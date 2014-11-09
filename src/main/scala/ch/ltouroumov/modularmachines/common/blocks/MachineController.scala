package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.tileentity.MachineControllerEntity
import net.minecraft.block.BlockContainer
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

class MachineController extends MachineComponent {
  setBlockName("Machine Controller")

  var facing = ForgeDirection.UNKNOWN

  override def createTileEntity(world: World, meta: Int) =
    new MachineControllerEntity(meta: Int)

  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entityliving: EntityLivingBase, itemStack: ItemStack): Unit = {
    
  }
}
