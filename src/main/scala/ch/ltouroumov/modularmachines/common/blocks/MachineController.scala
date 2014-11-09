package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.tileentity.MachineControllerEntity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.{IIcon, MathHelper}
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

class MachineController extends MachineComponent {
  setBlockName("Machine Controller")

  var facing = ForgeDirection.UNKNOWN

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    world.getTileEntity(x, y, z) match {
      case te: MachineControllerEntity =>
        textureHandler.getTexture(world, x, y, z, side)
      case _ =>
        textureHandler.getTexture(world, x, y, z, side)
    }


  override def createTileEntity(world: World, meta: Int) =
    new MachineControllerEntity(meta: Int)

  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entityliving: EntityLivingBase, itemStack: ItemStack): Unit = {
    val facing = ForgeDirection.getOrientation(MathHelper.floor_double(((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3)

    world.getTileEntity(x, y, z) match {
      case te: MachineControllerEntity =>
        te.facing = facing
      case _ => ()
    }
  }
}
