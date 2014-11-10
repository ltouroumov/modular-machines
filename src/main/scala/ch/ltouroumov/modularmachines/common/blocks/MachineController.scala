package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.texture.RotatableTextureHandler
import ch.ltouroumov.modularmachines.common.tileentity.MachineControllerEntity
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}

class MachineController extends MachineComponent with ITileEntityProvider {
  setBlockName("Machine Controller")

  val rotatableTextureHandler = new RotatableTextureHandler(Settings.assetName("Controller_Face"), defaultTextureHandler)

  override def registerBlockIcons(register: IIconRegister) =
    rotatableTextureHandler.loadTextures(register)

  override def getIcon(side: Int, meta: Int): IIcon =
    rotatableTextureHandler.getTexture(side)

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    rotatableTextureHandler.getTexture(world, x, y, z, side)

  override def createNewTileEntity(world: World, meta: Int) =
    new MachineControllerEntity
}
