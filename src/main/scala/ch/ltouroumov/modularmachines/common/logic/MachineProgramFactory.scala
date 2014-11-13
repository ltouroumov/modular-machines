package ch.ltouroumov.modularmachines.common.logic

import ch.ltouroumov.modularmachines.common.logic.program.MachineProgramBase
import ch.ltouroumov.modularmachines.utils.annotation.MachineProgram

object MachineProgramFactory {

  var programs: Map[String, Class[_]] = Map.empty

  def registerAll(klasses: Array[Class[_]]): Unit = {
    klasses.foreach(register)
  }

  def register(klass: Class[_]) = {
    val annotation = klass.getAnnotation[MachineProgram](classOf[MachineProgram])
    if (annotation != null)
      programs += (annotation.name() -> klass)
  }

  def create(name: String): MachineProgramBase = {
    if (programs.contains(name))
      programs(name).newInstance().asInstanceOf[MachineProgramBase]
    else
      null
  }

}
