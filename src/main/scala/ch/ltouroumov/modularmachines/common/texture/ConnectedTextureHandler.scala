package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.block.Block
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.{Facing, IIcon}
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

  def getTexture(side: Int): IIcon = {
    textures("All_Closed")
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
  
  def shouldRenderSide(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int) : Boolean = {
    true
    //!searchAt(x + Facing.offsetsXForSide(side), y + Facing.offsetsYForSide(side), z + Facing.offsetsZForSide(side), orOpaque = true)(world)
    /*ForgeDirection.getOrientation(side) match {
      case ForgeDirection.UP    => !searchAt(x, y + 1, z)
      case ForgeDirection.DOWN  => !searchAt(x, y - 1, z)
      case ForgeDirection.NORTH => !searchAt(x, y, z - 1)
      case ForgeDirection.SOUTH => !searchAt(x, y, z + 1)
      case ForgeDirection.WEST  => !searchAt(x - 1, y, z)
      case ForgeDirection.EAST  => !searchAt(x + 1, y, z)
      case _ => true
    }*/
  }
  
  case class Bitmask(north: Int, south: Int,
                     east: Int, west: Int,
                     ne: Int, nw: Int,
                     se: Int, sw: Int)

  def searchXZ(x :Int, y: Int, z: Int)(implicit world: IBlockAccess) : Bitmask =
    Bitmask(north = searchAtBit(x, y, z - 1),
            south = searchAtBit(x, y, z + 1),
            east  = searchAtBit(x + 1, y, z),
            west  = searchAtBit(x - 1, y, z),
            ne    = searchAtBit(x - 1, y, z - 1),
            nw    = searchAtBit(x + 1, y, z - 1),
            se    = searchAtBit(x - 1, y, z + 1),
            sw    = searchAtBit(x + 1, y, z + 1))

  def searchXY(x :Int, y: Int, z: Int, m: Int)(implicit world: IBlockAccess) : Bitmask = {
    Bitmask(north = searchAtBit(x, y + 1, z),
            south = searchAtBit(x, y - 1, z),
            east  = searchAtBit(x + m, y, z),
            west  = searchAtBit(x - m, y, z),
            ne    = searchAtBit(x - m, y + 1, z),
            nw    = searchAtBit(x + m, y + 1, z),
            se    = searchAtBit(x - m, y - 1, z),
            sw    = searchAtBit(x + m, y - 1, z))
  }

  def searchZY(x :Int, y: Int, z: Int, m: Int)(implicit world: IBlockAccess) : Bitmask = {
    Bitmask(north = searchAtBit(x, y + 1, z),
            south = searchAtBit(x, y - 1, z),
            east  = searchAtBit(x, y, z + m),
            west  = searchAtBit(x, y, z - m),
            ne    = searchAtBit(x, y + 1, z - m),
            nw    = searchAtBit(x, y + 1, z + m),
            se    = searchAtBit(x, y - 1, z - m),
            sw    = searchAtBit(x, y - 1, z + m))
  }
  
  def searchAt(x: Int, y: Int, z: Int, orOpaque: Boolean = false)(implicit world: IBlockAccess) : Boolean = {
    val block = world.getBlock(x, y, z)
    canConnect(block) || (orOpaque && block.isOpaqueCube)
  }
  
  def searchAtBit(x: Int, y: Int, z: Int)(implicit world: IBlockAccess) : Int = {
    if (searchAt(x, y, z)) 1
    else 0
  }
}
