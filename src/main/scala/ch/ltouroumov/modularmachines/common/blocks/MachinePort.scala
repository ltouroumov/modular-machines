package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.tileentity.{MachineControllerEntity, MachinePortEntity}
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}

class MachinePort extends MachineComponent with ITileEntityProvider {
  setBlockName("Machine Port")
  var faceIcon: IIcon = null

  override def registerBlockIcons(register: IIconRegister) = {
    super.registerBlockIcons(register)
    faceIcon = register.registerIcon("modularmachines:Ports_Item_In")
  }

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    world.getTileEntity(x, y, z) match {
      case te: MachinePortEntity =>
        if (side == te.facing)
          faceIcon
        else
          super.getIcon(world, x, y, z, side)
      case _ =>
        super.getIcon(world, x, y, z, side)
    }

  override def createNewTileEntity(world: World, meta: Int) =
    new MachinePortEntity
}
