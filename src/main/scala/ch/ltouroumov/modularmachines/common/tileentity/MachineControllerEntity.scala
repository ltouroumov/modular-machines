package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection

class MachineControllerEntity(val meta: Int) extends TileEntity {
  var facing: ForgeDirection = ForgeDirection.NORTH

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    tag.setInteger("facing", facing.ordinal())
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    facing = ForgeDirection.getOrientation(tag.getInteger("facing"))
  }
}
