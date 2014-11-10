package ch.ltouroumov.modularmachines.common.texture

import ch.ltouroumov.modularmachines.common.tileentity.RotatableEntity
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

class RotatableTextureHandler(val frontName: String, val delegate: TextureHandler) extends TextureHandler {

  var frontTexture: IIcon = null

  def loadTextures(register: IIconRegister) = {
    delegate.loadTextures(register)
    frontTexture = register.registerIcon(frontName)
  }

  def getTexture(side: Int): IIcon = {
    ForgeDirection.getOrientation(side) match {
      case ForgeDirection.NORTH | ForgeDirection.SOUTH =>
        frontTexture
      case _ =>
        delegate.getTexture(side)
    }
  }
  def getTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    world.getTileEntity(x, y, z) match {
      case rt: RotatableEntity if rt.facing == side =>
          frontTexture
      case _ =>
        delegate.getTexture(world, x, y, z, side)
    }
  }

}
