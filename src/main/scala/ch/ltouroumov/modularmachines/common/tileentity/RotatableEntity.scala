package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

trait RotatableEntity {
  // Not saved to NBT
  var oldFacing = ForgeDirection.NORTH

  var facing = ForgeDirection.NORTH

  def isFront(side: ForgeDirection) = side == facing
  def isBack(side: ForgeDirection) = side == facing.getOpposite

  def rotate(axis: ForgeDirection): Unit = {
    oldFacing = facing
    facing = facing.getRotation(axis)
  }

  def saveFacing(tag: NBTTagCompound): Unit = {
    tag.setInteger("facing", facing.ordinal)
  }

  def readFacing(tag: NBTTagCompound): Unit = {
    facing = ForgeDirection.getOrientation(tag.getInteger("facing"))
  }
}

object RotatableDummy