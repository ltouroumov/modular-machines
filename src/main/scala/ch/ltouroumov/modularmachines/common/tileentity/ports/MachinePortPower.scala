package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.PortType
import cofh.api.energy.IEnergyHandler
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

class MachinePortPower extends MachinePortBase with IEnergyHandler {
  def portType = PortType.Power

  var energyBuffer: Int = 0

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    tag.setInteger("EnergyBuffer", energyBuffer)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    energyBuffer = tag.getInteger("EnergyBuffer")
  }

  def canConnectEnergy(from: ForgeDirection): Boolean =
    from.ordinal() == facing

  def receiveEnergy(from: ForgeDirection, maxReceive: Int, simulate: Boolean): Int =
    if (from.ordinal() == facing)
      Math.min(MachinePortPower.maxPowerBuffer - energyBuffer, maxReceive)
    else 0

  def extractEnergy(from: ForgeDirection, maxExtract: Int, simulate: Boolean): Int = 0

  def getEnergyStored(from: ForgeDirection): Int =
    energyBuffer

  def getMaxEnergyStored(from: ForgeDirection): Int =
    MachinePortPower.maxPowerBuffer
}

object MachinePortPower {
  val maxPowerBuffer = 500000
}
