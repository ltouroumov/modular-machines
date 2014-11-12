package ch.ltouroumov.modularmachines

object Settings {

  val modid = "modularmachines"
  val namespace = modid + ":"

  def assetName(name: String) =
    String.format("%s:%s", modid, name)

}
