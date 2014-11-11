package ch.ltouroumov.modularmachines.common.utils

import net.minecraft.util.Facing
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class WorldUpdate(world: World) {

  def onSide(x: Int, y: Int, z: Int, side: ForgeDirection) = {
    val sideOrd = side.ordinal
    atCoords(x + Facing.offsetsXForSide(sideOrd),
             y + Facing.offsetsYForSide(sideOrd),
             z + Facing.offsetsZForSide(sideOrd))
  }

  def atCoords(x: Int, y:Int, z:Int) = {
    world.markBlockForUpdate(x, y, z)
  }

}

object WorldUpdate {
  def apply(world: World) = new WorldUpdate(world)
}