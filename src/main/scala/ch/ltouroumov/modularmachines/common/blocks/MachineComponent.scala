package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.{TextureHandler, ConnectedTextureHandler}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

abstract class MachineComponent extends Block(Material.ground) {

  setHardness(1.0F)
  setStepSound(Block.soundTypeMetal)
  setBlockName("MachineComponent")
  setCreativeTab(ModularMachines.tabModularMachines)

  def createTextureHandler: TextureHandler =
    new ConnectedTextureHandler(Settings.assetName("Frame_Side"), block => block.isInstanceOf[MachineComponent])

  lazy val defaultTextureHandler =
    createTextureHandler

  override def registerBlockIcons(register: IIconRegister) =
    defaultTextureHandler.loadTextures(register)

  override def getIcon(side:Int, meta:Int): IIcon =
    defaultTextureHandler.getTexture(side)

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    defaultTextureHandler.getTexture(world, x, y, z, side)
}
