package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.tileentity.{ModuleType, PortType}
import ch.ltouroumov.modularmachines.common.{blocks, tileentity}
import cpw.mods.fml.common.registry.GameRegistry

object Blocks extends ObjectRegistry {
  val machineFrame = new blocks.MachineFrame
  val machineController = new blocks.MachineController
  val machineGlass = new blocks.MachineGlass
  val machinePort_Items = new blocks.MachinePort(PortType.Items)
  val machinePort_Power = new blocks.MachinePort(PortType.Power)
  val machinePort_Fluid = new blocks.MachinePort(PortType.Fluid)
  val machineModule_Coil = new blocks.MachineModule(ModuleType.Coil)
  val machineModule_Smelter = new blocks.MachineModule(ModuleType.Smelter)

  def registerBlocks() {
    GameRegistry.registerBlock(machineFrame, Settings.namespace + "blockMachineFrame")
    GameRegistry.registerBlock(machineController, Settings.namespace + "blockMachineController")
    GameRegistry.registerBlock(machineGlass, Settings.namespace + "blockMachineGlass")
    GameRegistry.registerBlock(machinePort_Items, Settings.namespace + "blockMachinePort_Items")
    GameRegistry.registerBlock(machinePort_Power, Settings.namespace + "blockMachinePort_Power")
    GameRegistry.registerBlock(machinePort_Fluid, Settings.namespace + "blockMachinePort_Fluid")
    GameRegistry.registerBlock(machineModule_Coil, Settings.namespace + "blockMachinePort_Coil")
    GameRegistry.registerBlock(machineModule_Smelter, Settings.namespace + "blockMachinePort_Smelter")
  }

  def registerTileEntities() {
    GameRegistry.registerTileEntity(classOf[tileentity.MachineControllerEntity], Settings.namespace + "machineController")
    GameRegistry.registerTileEntity(classOf[tileentity.MachinePortEntity], Settings.namespace + "machinePort")
    GameRegistry.registerTileEntity(classOf[tileentity.MachineModuleEntity], Settings.namespace + "machineModule")
  }

  registerCallback(1, registerBlocks)
  registerCallback(2, registerTileEntities)
}
