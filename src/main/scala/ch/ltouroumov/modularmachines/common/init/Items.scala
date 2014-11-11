package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.common.items.{Wrench, Multimeter}
import cpw.mods.fml.common.registry.GameRegistry

object Items extends ObjectRegistry {
  val wrench = new Wrench
  val multimeter = new Multimeter

  def registerItems() {
    GameRegistry.registerItem(wrench, "itemWrench")
    GameRegistry.registerItem(multimeter, "itemMultimeter")
  }

  registerCallback(1, registerItems)
}
