package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

class NullTextureHandler extends TextureHandler {

  override def loadTextures(register: IIconRegister): Unit = {}
  override def getTexture(side: ForgeDirection): IIcon = null
  override def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection): IIcon = null
}
