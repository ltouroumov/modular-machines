package ch.ltouroumov.modularmachines

object Settings {

  val resourcesDomain = "modularmachines"
  val namespace = resourcesDomain + ":"

  def assetName(name: String) =
    String.format("%s:%s", resourcesDomain, name)

}
