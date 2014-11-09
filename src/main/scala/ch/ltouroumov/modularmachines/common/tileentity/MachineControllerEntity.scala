package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound

class MachineControllerEntity extends BaseEntity with RotatableEntity {

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    saveFacing(tag)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    readFacing(tag)
  }



}
