package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.texture.RotatableTextureHandler
import ch.ltouroumov.modularmachines.common.tileentity.ports.{MachinePortFluid, MachinePortPower, MachinePortItems, MachinePortBase}
import ch.ltouroumov.modularmachines.common.tileentity.PortType._
import ch.ltouroumov.modularmachines.common.tileentity.utils.{RotatableEntityDummy, RotatableEntity}
import net.minecraft.block.ITileEntityProvider
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class MachinePort(val pType: PortType) extends MachineComponent with ITileEntityProvider {
  setBlockName(pType match {
    case Items => "MachinePort_Items"
    case Power => "MachinePort_Power"
    case Fluid => "MachinePort_Fluid"
    case _ => "MachinePort_Unkown"
  })

  override def createTextureHandler =
    new RotatableTextureHandler(super.createTextureHandler, Settings.namespace) {
      override def sideTextureNames: List[String] = List(
        "Ports_Items_In", "Ports_Items_Out",
        "Ports_Power_In", "Ports_Power_Out",
        "Ports_Fluid_In", "Ports_Fluid_Out"
      )

      override def sideTextureFor(entity: RotatableEntity, side: ForgeDirection): Option[String] =
        entity match {
          case te: MachinePortBase if te.isFront(side) =>
            Some(String.format("Ports_%s_%s", te.portType, te.portDirection))
          case RotatableEntityDummy if side == ForgeDirection.SOUTH =>
            pType match {
              case Items => Some("Ports_Items_In")
              case Power => Some("Ports_Power_In")
              case Fluid => Some("Ports_Fluid_In")
            }
          case _ => None
        }
    }

  override def createNewTileEntity(world: World, meta: Int) =
    pType match {
      case Items => new MachinePortItems
      case Power => new MachinePortPower
      case Fluid => new MachinePortFluid
    }
}
