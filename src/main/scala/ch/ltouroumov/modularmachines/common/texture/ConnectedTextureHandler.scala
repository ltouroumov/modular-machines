package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.block.Block
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

class ConnectedTextureHandler[TBlock](val baseName: String, val canConnect: Block => Boolean) extends TextureHandler {

  var textureNames = List[String](
    "None", "All", "All_Closed", "NorthSouth", "WestEast",
    "North", "South", "East", "West",
    "NorthEast", "NorthWest", "SouthEast", "SouthWest",
    "North_Closed", "South_Closed", "East_Closed", "West_Closed",
    "North_Open", "South_Open", "East_Open", "West_Open",
    "NorthEast_Closed", "NorthWest_Closed", "SouthEast_Closed", "SouthWest_Closed"
  )
  var textures = Map[String, IIcon]()

  def loadTextures(register: IIconRegister) = {
    val pairs = for(name <- textureNames)
                yield (name, register.registerIcon(baseName + "_" + name))
    textures = pairs.toMap
  }

  // N = z- / S = z+
  // E = x+ / W = x-
  // U = y+ / D = y-
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    implicit val w = world
    val direction = ForgeDirection.getOrientation(side)
    val connected = direction match {
      case ForgeDirection.UP | ForgeDirection.DOWN => searchXZ(x, y, z)
      case ForgeDirection.NORTH => searchXY(x, y, z, -1)
      case ForgeDirection.SOUTH => searchXY(x, y, z, 1)
      case ForgeDirection.EAST  => searchZY(x, y, z, -1)
      case ForgeDirection.WEST => searchZY(x, y, z, 1)
      case _ => throw new IllegalArgumentException("Invalid side")
    }

    connected match {
        // Truth table
        // Bitmask(N, S, E, W,NE,NW,SE,SW)
        // Squares
      case Bitmask(1, 1, 1, 1, 1, 1, 1, 1) => textures("None")
      case Bitmask(0, 0, 0, 0, 0, 0, 0, 0) => textures("All_Closed")
      case Bitmask(1, 1, 1, 1, _, _, _, _) => textures("All")
        // Straight
      case Bitmask(1, 1, 0, 0, _, _, _, _) => textures("NorthSouth")
      case Bitmask(0, 0, 1, 1, _, _, _, _) => textures("WestEast")
        // Sides
      case Bitmask(0, 1, 1, 1, _, _, 1, 1) => textures("North")
      case Bitmask(1, 0, 1, 1, 1, 1, _, _) => textures("South")
      case Bitmask(1, 1, 0, 1, 1, _, 1, _) => textures("East")
      case Bitmask(1, 1, 1, 0, _, 1, _, 1) => textures("West")
        // T Junctions
      case Bitmask(0, 1, 1, 1, _, _, _, _) => textures("North_Open")
      case Bitmask(1, 0, 1, 1, _, _, _, _) => textures("South_Open")
      case Bitmask(1, 1, 0, 1, _, _, _, _) => textures("West_Open")
      case Bitmask(1, 1, 1, 0, _, _, _, _) => textures("East_Open")
      // U Junctions
      case Bitmask(0, 0, 0, 1, _, _, _, _) => textures("East_Closed")
      case Bitmask(0, 0, 1, 0, _, _, _, _) => textures("West_Closed")
      case Bitmask(0, 1, 0, 0, _, _, _, _) => textures("North_Closed")
      case Bitmask(1, 0, 0, 0, _, _, _, _) => textures("South_Closed")
        // Angles
      case Bitmask(1, 0, 0, 1, 0, _, _, _) => textures("SouthEast_Closed")
      case Bitmask(1, 0, 1, 0, _, 0, _, _) => textures("SouthWest_Closed")
      case Bitmask(0, 1, 0, 1, _, _, 0, _) => textures("NorthEast_Closed")
      case Bitmask(0, 1, 1, 0, _, _, _, 0) => textures("NorthWest_Closed")

      case Bitmask(1, 0, 0, 1, 1, _, _, _) => textures("SouthEast")
      case Bitmask(1, 0, 1, 0, _, 1, _, _) => textures("SouthWest")
      case Bitmask(0, 1, 0, 1, _, _, 1, _) => textures("NorthEast")
      case Bitmask(0, 1, 1, 0, _, _, _, 1) => textures("NorthWest")
      case mask =>
        System.out.println(mask)
        textures("All_Closed")
    }
  }
  
  case class Bitmask(north: Int,
                     south: Int,
                     east: Int,
                     west: Int,
                     ne: Int,
                     nw: Int,
                     se: Int,
                     sw: Int)

  def searchXZ(x :Int, y: Int, z: Int)(implicit world: IBlockAccess) : Bitmask =
    Bitmask(north = searchAt(x, y, z - 1),
            south = searchAt(x, y, z + 1),
            east  = searchAt(x + 1, y, z),
            west  = searchAt(x - 1, y, z),
            ne    = searchAt(x - 1, y, z - 1),
            nw    = searchAt(x + 1, y, z - 1),
            se    = searchAt(x - 1, y, z + 1),
            sw    = searchAt(x + 1, y, z + 1))

  def searchXY(x :Int, y: Int, z: Int, m: Int)(implicit world: IBlockAccess) : Bitmask = {
    Bitmask(north = searchAt(x, y + 1, z),
            south = searchAt(x, y - 1, z),
            east  = searchAt(x + m, y, z),
            west  = searchAt(x - m, y, z),
            ne    = searchAt(x - m, y + 1, z),
            nw    = searchAt(x + m, y + 1, z),
            se    = searchAt(x - m, y - 1, z),
            sw    = searchAt(x + m, y - 1, z))
  }

  def searchZY(x :Int, y: Int, z: Int, m: Int)(implicit world: IBlockAccess) : Bitmask = {
    Bitmask(north = searchAt(x, y + 1, z),
            south = searchAt(x, y - 1, z),
            east  = searchAt(x, y, z + m),
            west  = searchAt(x, y, z - m),
            ne    = searchAt(x, y + 1, z - m),
            nw    = searchAt(x, y + 1, z + m),
            se    = searchAt(x, y - 1, z - m),
            sw    = searchAt(x, y - 1, z + m))
  }

  def searchAt(x: Int, y: Int, z: Int)(implicit world: IBlockAccess) : Int = {
    val block = world.getBlock(x, y, z)
    if (canConnect(block)) 1
    else 0
  }
}
