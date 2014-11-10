package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.ConnectedTextureHandler
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

class MachineGlass extends Block(Material.glass) {

  setHardness(1.0F)
  setStepSound(Block.soundTypeGlass)
  setBlockName("MachineGlass")
  setCreativeTab(ModularMachines.tabModularMachines)

  val textureHandler = new ConnectedTextureHandler(Settings.assetName("Window_Side"), block => block.isInstanceOf[MachineGlass])

  override def registerBlockIcons(register: IIconRegister) =
    textureHandler.loadTextures(register)

  override def getIcon(side:Int, meta:Int): IIcon =
    textureHandler.getTexture(side)

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    textureHandler.getTexture(world, x, y, z, side)

  override def shouldSideBeRendered(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Boolean =
    textureHandler.shouldRenderSide(world, x, y, z, side)

  override def isOpaqueCube = false
}
