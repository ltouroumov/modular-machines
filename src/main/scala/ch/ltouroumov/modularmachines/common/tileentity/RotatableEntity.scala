package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

trait RotatableEntity {
  var facing: Int = ForgeDirection.NORTH.ordinal()

  def rotate(axis: ForgeDirection): Unit = {
    facing = ForgeDirection.getOrientation(facing).getRotation(axis).ordinal()
  }

  def saveFacing(tag: NBTTagCompound): Unit = {
    tag.setInteger("facing", facing)
  }

  def readFacing(tag: NBTTagCompound): Unit = {
    facing = tag.getInteger("facing")
  }
}
