package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.tileentity.MachineControllerEntity
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}

class MachineController extends MachineComponent with ITileEntityProvider {
  setBlockName("Machine Controller")
  var faceIcon: IIcon = null

  override def registerBlockIcons(register: IIconRegister) = {
    super.registerBlockIcons(register)
    faceIcon = register.registerIcon("modularmachines:Controller_Face")
  }

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    world.getTileEntity(x, y, z) match {
      case te: MachineControllerEntity =>
        if (side == te.facing)
          faceIcon
        else
          super.getIcon(world, x, y, z, side)
      case _ =>
        super.getIcon(world, x, y, z, side)
    }

  override def createNewTileEntity(world: World, meta: Int) =
    new MachineControllerEntity

  //TODO: Place in the direction facing the player
  /*override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack): Unit = {
    System.out.println("YawHead: " + entity.rotationYaw)
    val angle = entity.rotationYaw + 180
    System.out.println("Angle: " + angle)

    val facing = ForgeDirection.DOWN
    world.getTileEntity(x, y, z) match {
      case te: MachineControllerEntity =>
        te.facing = facing.getOpposite.ordinal()
        world.markBlockForUpdate(x, y, z)
      case _ => ()
    }
  }*/
}
