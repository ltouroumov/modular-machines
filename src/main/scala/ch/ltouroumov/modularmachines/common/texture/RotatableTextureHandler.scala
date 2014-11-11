package ch.ltouroumov.modularmachines.common.texture

import ch.ltouroumov.modularmachines.common.tileentity.{RotatableEntityDummy, RotatableEntity}
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
    sideTextureFor(RotatableEntityDummy, side) map frontTextures.apply getOrElse delegate.getTexture(side)
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
  def simpleHandler(name: String, delegate: TextureHandler) =
    new RotatableTextureHandler(delegate) {
      override def sideTextureNames: List[String] =
        List(name)

      override def sideTextureFor(entity: RotatableEntity, side: ForgeDirection): Option[String] =
        entity match {
          case rt: RotatableEntity if rt.isFront(side) =>
            Some(name)
          case RotatableEntityDummy if side == ForgeDirection.SOUTH =>
            Some(name)
          case _ =>
            None
        }
    }
  
}
