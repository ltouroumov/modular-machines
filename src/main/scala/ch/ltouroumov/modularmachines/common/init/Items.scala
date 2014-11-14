package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.common.items.controller.{MachineCardItem, MachineProgramItem}
import ch.ltouroumov.modularmachines.common.items.{Wrench, Multimeter}
import cpw.mods.fml.common.registry.GameRegistry

object Items extends ObjectRegistry {
  val wrench = new Wrench
  val multimeter = new Multimeter
  val machineProgram = new MachineProgramItem
  val machineCard = new MachineCardItem

  def registerItems() {
    GameRegistry.registerItem(wrench, "itemWrench")
    GameRegistry.registerItem(multimeter, "itemMultimeter")

    GameRegistry.registerItem(machineProgram, "itemMachineProgram")
    GameRegistry.registerItem(machineCard, "itemMachineCard")
  }

  registerCallback(1, registerItems)
}
