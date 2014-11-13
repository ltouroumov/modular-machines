package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.PortType
import ch.ltouroumov.modularmachines.common.tileentity.utils.DiagnosableEntity
import ch.ltouroumov.modularmachines.utils.annotation.{EntitySaveHandler, EntityLoadHandler}
import cofh.api.energy.IEnergyHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

class MachinePortPower extends MachinePortBase with IEnergyHandler with DiagnosableEntity {
  def portType = PortType.Power

  var energyBuffer: Int = 0

  @EntitySaveHandler
  def saveBuffer(tag: NBTTagCompound): Unit = {
    tag.setInteger("EnergyBuffer", energyBuffer)
  }

  @EntityLoadHandler
  def loadBuffer(tag: NBTTagCompound): Unit = {
    energyBuffer = tag.getInteger("EnergyBuffer")
  }

  def canConnectEnergy(from: ForgeDirection): Boolean = isFront(from)

  def receiveEnergy(from: ForgeDirection, maxReceive: Int, simulate: Boolean): Int =
    if (canConnectEnergy(from)) {
      val energy = Math.min(MachinePortPower.maxPowerBuffer - energyBuffer, maxReceive)
      if (!simulate)
        energyBuffer += energy
      energy
    } else 0

  def extractEnergy(from: ForgeDirection, maxExtract: Int, simulate: Boolean): Int = 0

  def getEnergyStored(from: ForgeDirection): Int =
    energyBuffer

  def getMaxEnergyStored(from: ForgeDirection): Int =
    MachinePortPower.maxPowerBuffer

  def onDiagnose(message: StringBuilder, player: EntityPlayer, x: Int, y: Int, z:Int, side: Int) = {
    message.append(s"Power Buffer: $energyBuffer / ${MachinePortPower.maxPowerBuffer}")
  }
}

object MachinePortPower {
  val maxPowerBuffer = 500000
}
