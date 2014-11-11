package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

trait RotatableEntity {
  var facing: ForgeDirection = ForgeDirection.NORTH

  def isFront(side: ForgeDirection) = side == facing
  def isBack(side: ForgeDirection) = side == facing.getOpposite

  def rotate(axis: ForgeDirection): Unit = {
    facing = facing.getRotation(axis)
  }

  def saveFacing(tag: NBTTagCompound): Unit = {
    tag.setInteger("facing", facing.ordinal)
  }

  def readFacing(tag: NBTTagCompound): Unit = {
    facing = ForgeDirection.getOrientation(tag.getInteger("facing"))
  }
}