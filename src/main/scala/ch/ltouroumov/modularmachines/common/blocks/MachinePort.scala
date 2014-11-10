package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.tileentity.MachinePortEntity
import ch.ltouroumov.modularmachines.common.tileentity.PortType._
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

class MachinePort(val pType: PortType) extends MachineComponent with ITileEntityProvider {
  setBlockName(pType match {
    case Items => "MachinePort_Items"
    case Power => "MachinePort_Power"
    case Fluid => "MachinePort_Fluid"
    case _ => "MachinePort_Unkown"
  })
  val baseName = "modularmachines:Ports_"
  val faceNames = Array(
    "Items_In", "Items_Out",
    "Power_In", "Power_Out",
    "Fluid_In", "Fluid_Out"
  )

  var faceIcons = Map.empty[String, IIcon]
  override def registerBlockIcons(register: IIconRegister) = {
    super.registerBlockIcons(register)
    for(texName <- faceNames) {
      val fullTexName = baseName + texName
      val icon = register.registerIcon(fullTexName)
      faceIcons = faceIcons.updated(texName, icon)
    }
  }

  override def getIcon(side:Int, meta:Int): IIcon = {
    ForgeDirection.getOrientation(side) match {
      case ForgeDirection.NORTH|ForgeDirection.SOUTH => pType match {
        case Items => faceIcons("Items_In")
        case Power => faceIcons("Power_In")
        case Fluid => faceIcons("Fluid_In")
      }
      case _ => super.getIcon(side, meta)
    }
  }

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    world.getTileEntity(x, y, z) match {
      case te: MachinePortEntity if te.facing == side =>
        val texName = String.format("%s_%s", te.portType, te.portDirection)
        faceIcons(texName)
      case _ =>
        super.getIcon(world, x, y, z, side)
    }

  override def createNewTileEntity(world: World, meta: Int) =
    new MachinePortEntity(pType)
}
