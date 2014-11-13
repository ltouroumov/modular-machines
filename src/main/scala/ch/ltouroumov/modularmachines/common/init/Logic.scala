package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.common.logic.card.PowerEfficiencyCard
import ch.ltouroumov.modularmachines.common.logic.{MachineCardFactory, MachineProgramFactory}
import ch.ltouroumov.modularmachines.common.logic.program.{GrinderProgram, SmelterProgram}

object Logic extends ObjectRegistry {

  def registerPrograms(): Unit = {
    MachineProgramFactory.registerAll(Array(
      classOf[SmelterProgram],
      classOf[GrinderProgram]
    ))
  }

  def registerCards(): Unit ={
    MachineCardFactory.registerAll(Array(
      classOf[PowerEfficiencyCard],
      classOf[PowerEfficiencyCard]
    ))
  }

  registerCallback(1, registerPrograms)
  registerCallback(2, registerCards)
}
