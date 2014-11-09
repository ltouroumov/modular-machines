package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound

class MachinePortEntity extends BaseEntity with RotatableEntity {
  var portType = PortType.Items
  var portDirection = PortDirection.In

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    saveFacing(tag)
    tag.setInteger("Type", portType.id)
    tag.setInteger("Direction", portDirection.id)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    readFacing(tag)
    portType = PortType(tag.getInteger("Type"))
    portDirection = PortDirection(tag.getInteger("Direction"))
  }

}

object PortType extends Enumeration {
  type PortType = Value
  val Items = Value("Items")
  val Power = Value("Power")
  val Fluid = Value("Fluid")
}

object PortDirection extends Enumeration {
  type PortDirection = Value
  val In = Value("In")
  val Out = Value("Out")
}