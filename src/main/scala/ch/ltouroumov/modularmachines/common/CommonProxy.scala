package ch.ltouroumov.modularmachines.common

import ch.ltouroumov.modularmachines.common.init.{Recipes, Items, Blocks}
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

abstract class CommonProxy {
  def preInit(evt: FMLPreInitializationEvent) = {

  }
  def onInit(evt: FMLInitializationEvent) = {
    Blocks.register()
    Items.register()
    Recipes.register()

    registerRenders()
  }
  def postInit(evt: FMLPostInitializationEvent) = {

  }

  def registerRenders() = {

  }
}
