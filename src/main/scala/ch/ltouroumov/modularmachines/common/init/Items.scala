package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.items
import cpw.mods.fml.common.registry.GameRegistry

object Items extends ObjectRegistry {
  val wrench = new items.Wrench

  def registerItems() {
    GameRegistry.registerItem(wrench, Settings.namespace + "itemWrench")
  }

  registerCallback(1, registerItems)
}
