package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.ConnectedTextureHandler
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

abstract class MachineComponent extends Block(Material.ground) {

  setHardness(1.0F)
  setStepSound(Block.soundTypeMetal)
  setBlockName("Machine Component")
  setCreativeTab(ModularMachines.tabModularMachines)
  setBlockTextureName("modularmachines:Machine_Side_All")

  val textureHandler = new ConnectedTextureHandler("modularmachines:Frame_Side", block => block.isInstanceOf[MachineComponent])

  override def registerBlockIcons(register: IIconRegister) =
    textureHandler.loadTextures(register)

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    textureHandler.getTexture(world, x, y, z, side)
}
