package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.{RotatableTextureHandler, SidedTextureHandler}
import ch.ltouroumov.modularmachines.common.tileentity.MachineModuleEntity
import ch.ltouroumov.modularmachines.common.tileentity.ModuleType._
import net.minecraft.block.{ITileEntityProvider, Block}
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{World, IBlockAccess}
import net.minecraftforge.common.util.ForgeDirection

class MachineModule(mType: ModuleType) extends Block(Material.iron) with ITileEntityProvider {

  setHardness(1.0F)
  setStepSound(Block.soundTypeMetal)
  setBlockName(mType match {
    case Smelter => "MachineModule_Smelter"
    case Coil => "MachineModule_Coil"
    case _ => "MachineModule_Unkown"
  })
  setCreativeTab(ModularMachines.tabModularMachines)

  override def createNewTileEntity(world: World, meta: Int) =
    new MachineModuleEntity(mType)

  val frontTextureMap = Map(
    Smelter -> Settings.assetName("Module_Smelter"),
    Coil -> Settings.assetName("Module_Coil")
  )
  val sideTextureMap = Map(
    ForgeDirection.UP    -> Settings.assetName("Module_Panel_Top"),
    ForgeDirection.DOWN  -> Settings.assetName("Module_Panel_Top"),
    ForgeDirection.NORTH -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.SOUTH -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.EAST  -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.WEST  -> Settings.assetName("Module_Panel_Side")
  )
  val sidedTextureHandler = new SidedTextureHandler(sideTextureMap)
  val rotatableTextureHandler = new RotatableTextureHandler(frontTextureMap(mType), sidedTextureHandler)

  override def registerBlockIcons(register: IIconRegister) =
    rotatableTextureHandler.loadTextures(register)

  override def getIcon(side: Int, meta: Int): IIcon =
    rotatableTextureHandler.getTexture(side)

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z:Int, side: Int): IIcon =
    rotatableTextureHandler.getTexture(world, x, y, z, side)
}