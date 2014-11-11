package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.{RotatableTextureHandler, SidedTextureHandler}
import ch.ltouroumov.modularmachines.common.tileentity.ModuleType._
import ch.ltouroumov.modularmachines.common.tileentity.modules.{MachineModuleCoil, MachineModuleSmelter}
import net.minecraft.block.{ITileEntityProvider, Block}
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class MachineModule(mType: ModuleType) extends MachineComponent with ITileEntityProvider {

  setHardness(1.0F)
  setStepSound(Block.soundTypeMetal)
  setBlockName(mType match {
    case Smelter => "MachineModule_Smelter"
    case Coil => "MachineModule_Coil"
    case _ => "MachineModule_Unkown"
  })
  setCreativeTab(ModularMachines.tabModularMachines)

  override def createNewTileEntity(world: World, meta: Int) =
    mType match {
      case Smelter => new MachineModuleSmelter
      case Coil => new MachineModuleCoil
    }

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

  override def createTextureHandler =
    RotatableTextureHandler.simpleHandler(frontTextureMap(mType), new SidedTextureHandler(sideTextureMap))
}