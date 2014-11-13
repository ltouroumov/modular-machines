package ch.ltouroumov.modularmachines.common.logic.program

import ch.ltouroumov.modularmachines.utils.annotation.MachineProgram
import net.minecraft.nbt.NBTTagCompound

abstract class MachineProgramBase {
  lazy val name = try {
    this.getClass.getAnnotation[MachineProgram](classOf[MachineProgram]).name()
  } catch {
    case ex: RuntimeException => "UnkownProgram"
  }

    def saveData(tag: NBTTagCompound) = {}
    def loadData(tag: NBTTagCompound) = {}
}