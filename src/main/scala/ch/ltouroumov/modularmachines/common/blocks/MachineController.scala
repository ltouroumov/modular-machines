package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.texture.RotatableTextureHandler
import ch.ltouroumov.modularmachines.common.tileentity.MachineControllerEntity
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.IIcon
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
    RotatableTextureHandler.simpleHandler(Settings.assetName("Controller_Face"), super.createTextureHandler)

  override def createNewTileEntity(world: World, meta: Int) =
    new MachineControllerEntity
}
