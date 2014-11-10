package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.texture.RotatableTextureHandler
import ch.ltouroumov.modularmachines.common.tileentity.ports.{MachinePortFluid, MachinePortPower, MachinePortItems, MachinePortBase}
import ch.ltouroumov.modularmachines.common.tileentity.RotatableEntity
import ch.ltouroumov.modularmachines.common.tileentity.PortType._
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

  val baseName = "modularmachines:Ports_"
  override def createTextureHandler =
    new RotatableTextureHandler(baseName, super.createTextureHandler) {
      override def frontTextureNames: List[String] = List(
        "Items_In", "Items_Out",
        "Power_In", "Power_Out",
        "Fluid_In", "Fluid_Out"
      )

      override def frontTextureFor(entity: RotatableEntity, side: ForgeDirection): String =
        entity match {
          case te: MachinePortBase =>
            String.format("%s_%s", te.portType, te.portDirection)
          case _ =>
            pType match {
              case Items => "Items_In"
              case Power => "Power_In"
              case Fluid => "Fluid_In"
            }
        }
    }

  override def createNewTileEntity(world: World, meta: Int) =
    pType match {
      case Items => new MachinePortItems
      case Power => new MachinePortPower
      case Fluid => new MachinePortFluid
    }
}
