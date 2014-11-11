package ch.ltouroumov.modularmachines.common.blocks

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.texture.{RotatableTextureHandler, SidedTextureHandler}
import ch.ltouroumov.modularmachines.common.tileentity.ModuleType._
import ch.ltouroumov.modularmachines.common.tileentity.{RotatableEntityDummy, RotatableEntity}
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

  val sideTextureMap = Map(
    ForgeDirection.UP    -> Settings.assetName("Module_Panel_Top"),
    ForgeDirection.DOWN  -> Settings.assetName("Module_Panel_Top"),
    ForgeDirection.NORTH -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.SOUTH -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.EAST  -> Settings.assetName("Module_Panel_Side"),
    ForgeDirection.WEST  -> Settings.assetName("Module_Panel_Side")
  )

  val sidedTextureHandler = new SidedTextureHandler(sideTextureMap)
  override def createTextureHandler =
    mType match {
      case Smelter =>
        RotatableTextureHandler.simpleHandler(Settings.assetName("Module_Smelter"), sidedTextureHandler)

      case Coil =>
        new RotatableTextureHandler(sidedTextureHandler, Settings.namespace) {
          override def sideTextureNames: List[String] =
            List("Module_Coil_Front", "Module_Coil_Back")
          override def sideTextureFor(entity: RotatableEntity, side: ForgeDirection): Option[String] =
            entity match {
              case mm: MachineModuleCoil =>
                side match {
                  case s if entity.isFront(s) => Some("Module_Coil_Front")
                  case s if entity.isBack(s) => Some("Module_Coil_Back")
                  case _ => None
                }
              case RotatableEntityDummy if side == ForgeDirection.SOUTH =>
                Some("Module_Coil_Front")
              case _ =>
                None
            }
        }
    }
}