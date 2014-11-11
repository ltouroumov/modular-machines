package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.{DiagnosableEntity, PortType}
import cofh.api.energy.IEnergyHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class MachinePortPower extends MachinePortBase with IEnergyHandler with DiagnosableEntity {
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

  def onDiagnose(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int): Map[String, String] =
    Map(
      "Power Buffer" -> s"${energyBuffer} / ${MachinePortPower.maxPowerBuffer}"
    )
}

object MachinePortPower {
  val maxPowerBuffer = 500000
}
