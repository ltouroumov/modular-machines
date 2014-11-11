package ch.ltouroumov.modularmachines.common.texture

import ch.ltouroumov.modularmachines.common.tileentity.RotatableEntity
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

abstract class RotatableTextureHandler(val baseName: String, val delegate: TextureHandler) extends TextureHandler {

  def frontTextureNames: List[String]
  def frontTextureFor(entity: RotatableEntity, side: ForgeDirection): String

  var frontTextures = Map.empty[String, IIcon]

  def loadTextures(register: IIconRegister) = {
    delegate.loadTextures(register)
    for (name <- frontTextureNames)
      frontTextures = frontTextures.updated(name, register.registerIcon(baseName + name))
  }

  def getTexture(side: Int): IIcon = {
    ForgeDirection.getOrientation(side) match {
      case fd @ ForgeDirection.SOUTH =>
        val texName = frontTextureFor(null, fd)
        frontTextures(texName)
      case _ =>
        delegate.getTexture(side)
    }
  }
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    world.getTileEntity(x, y, z) match {
      case rt: RotatableEntity if rt.facing == side =>
        val texName = frontTextureFor(rt, ForgeDirection.getOrientation(side))
        frontTextures(texName)
      case _ =>
        delegate.getTexture(world, x, y, z, side)
    }
  }
}

object RotatableTextureHandler {
  
  def simpleHandler(name: String, delegate: TextureHandler) =
    new RotatableTextureHandler("", delegate) {
      override def frontTextureNames: List[String] =
        List(name)

      override def frontTextureFor(entity: RotatableEntity, side: ForgeDirection): String =
        name
    }
  
}
