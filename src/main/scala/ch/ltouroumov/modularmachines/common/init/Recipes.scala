package ch.ltouroumov.modularmachines.common.init

object Recipes extends ObjectRegistry {

  def registerBlockRecipes() {

  }

  def registerItemRecipes() {

  }

  registerCallback(1, registerBlockRecipes)
  registerCallback(2, registerItemRecipes)

}
