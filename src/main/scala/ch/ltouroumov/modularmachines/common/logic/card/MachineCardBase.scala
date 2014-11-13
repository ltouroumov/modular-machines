package ch.ltouroumov.modularmachines.common.logic.card

import ch.ltouroumov.modularmachines.utils.annotation.MachineProgram
import net.minecraft.nbt.NBTTagCompound

abstract class MachineCardBase {
  lazy val name = try {
    this.getClass.getAnnotation[MachineProgram](classOf[MachineProgram]).name()
  } catch {
    case ex: RuntimeException => "UnkownProgram"
  }

  def saveData(tag: NBTTagCompound) = {}
  def loadData(tag: NBTTagCompound) = {}
}
