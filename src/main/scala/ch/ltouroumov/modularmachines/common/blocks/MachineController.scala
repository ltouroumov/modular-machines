package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.common.texture.NullTextureHandler
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import net.minecraft.block.ITileEntityProvider
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.{IBlockAccess, World}

class MachineController extends MachineComponent with ITileEntityProvider {
  setBlockName("Machine Controller")



  override def shouldSideBeRendered(world : IBlockAccess, x: Int, y: Int, z: Int, side: Int): Boolean =
    false

  override def isOpaqueCube: Boolean =
    false

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, xf: Float, yf: Float, zf: Float): Boolean = {
    false
  }

  override def createTextureHandler =
    new NullTextureHandler()
    //RotatableTextureHandler.simpleHandler(Settings.assetName("Controller_Face"), super.createTextureHandler)

  override def createNewTileEntity(world: World, meta: Int) =
    new MachineControllerEntity
}
