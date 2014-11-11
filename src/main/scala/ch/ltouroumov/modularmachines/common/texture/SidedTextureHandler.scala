package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

class SidedTextureHandler(val names: Map[ForgeDirection, String]) extends TextureHandler {

  var textures = Map.empty[ForgeDirection, IIcon]

  def loadTextures(register: IIconRegister) {
    for ( (side, name) <- names ) {
      textures = textures.updated(side, register.registerIcon(name))
    }
  }

  def getTexture(side: ForgeDirection): IIcon = {
    textures(side)
  }

  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection): IIcon = {
    getTexture(side)
  }

}
