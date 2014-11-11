package ch.ltouroumov.modularmachines.common.texture

import ch.ltouroumov.modularmachines.common.tileentity.RotatableEntity
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

abstract class RotatableTextureHandler(val delegate: TextureHandler, val baseName: String = "") extends TextureHandler {

  def sideTextureNames: List[String]
  def sideTextureFor(entity: RotatableEntity, side: ForgeDirection): Option[String]

  var frontTextures = Map.empty[String, IIcon]

  def loadTextures(register: IIconRegister) = {
    delegate.loadTextures(register)
    for (name <- sideTextureNames)
      frontTextures = frontTextures.updated(name, register.registerIcon(baseName + name))
  }

  def getTexture(side: ForgeDirection): IIcon = {
    sideTextureFor(RotatableTextureHandler.Dummy, side) map frontTextures.apply getOrElse delegate.getTexture(side)
  }
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection): IIcon = {
    world.getTileEntity(x, y, z) match {
      case rt: RotatableEntity =>
        sideTextureFor(rt, side) map { frontTextures(_) } getOrElse { delegate.getTexture(world, x, y, z, side) }
      case _ =>
        delegate.getTexture(world, x, y, z, side)
    }
  }
}

object RotatableTextureHandler {
  object Dummy extends RotatableEntity {
    facing = ForgeDirection.SOUTH
  }

  def simpleHandler(name: String, delegate: TextureHandler) =
    new RotatableTextureHandler(delegate) {
      override def sideTextureNames: List[String] =
        List(name)

      override def sideTextureFor(entity: RotatableEntity, side: ForgeDirection): Option[String] =
        if (entity != null && entity.isFront(side))
          Some(name)
        else
          None
    }
  
}
