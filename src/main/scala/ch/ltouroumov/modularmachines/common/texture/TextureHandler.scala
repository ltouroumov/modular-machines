package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

abstract class TextureHandler {

  def loadTextures(register: IIconRegister)
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon

}
