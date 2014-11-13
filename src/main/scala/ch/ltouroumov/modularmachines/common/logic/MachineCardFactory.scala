package ch.ltouroumov.modularmachines.common.logic

import ch.ltouroumov.modularmachines.common.logic.card.MachineCardBase
import ch.ltouroumov.modularmachines.utils.annotation.MachineCard

object MachineCardFactory {
  var cards: Map[String, Class[_]] = Map.empty

  def registerAll(klasses: Array[Class[_]]): Unit = {
    klasses.foreach(register)
  }

  def register(klass: Class[_]) = {
    val annotation = klass.getAnnotation[MachineCard](classOf[MachineCard])
    if (annotation != null)
      cards += (annotation.name() -> klass)
  }


  def create(name: String): MachineCardBase = {
    if (cards.contains(name))
      cards(name).newInstance().asInstanceOf[MachineCardBase]
    else
      null
  }
}
