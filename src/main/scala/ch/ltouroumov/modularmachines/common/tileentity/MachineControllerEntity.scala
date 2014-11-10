package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class MachineControllerEntity extends BaseEntity with RotatableEntity with WrenchableEntity {

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    saveFacing(tag)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    readFacing(tag)
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }

}
