package ch.ltouroumov.modularmachines.common.texture

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

abstract class TextureHandler {

  def loadTextures(register: IIconRegister)
  def getTexture(side: ForgeDirection): IIcon
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection): IIcon

  def getTexture(side: Int): IIcon =
    getTexture(ForgeDirection.getOrientation(side))
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon =
    getTexture(world, x, y, z, ForgeDirection.getOrientation(side))

}
