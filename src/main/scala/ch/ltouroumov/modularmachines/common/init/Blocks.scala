package ch.ltouroumov.modularmachines.common.init

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.tileentity.modules.{MachineModuleCoil, MachineModuleSmelter}
import ch.ltouroumov.modularmachines.common.tileentity.ports.{MachinePortItems, MachinePortFluid, MachinePortPower}
import ch.ltouroumov.modularmachines.common.tileentity.{MachineControllerEntity, ModuleType, PortType}
import ch.ltouroumov.modularmachines.common.blocks
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
    GameRegistry.registerBlock(machineFrame, "blockMachineFrame")
    GameRegistry.registerBlock(machineController, "blockMachineController")
    GameRegistry.registerBlock(machineGlass, "blockMachineGlass")
    GameRegistry.registerBlock(machinePort_Items, "blockMachinePort_Items")
    GameRegistry.registerBlock(machinePort_Power, "blockMachinePort_Power")
    GameRegistry.registerBlock(machinePort_Fluid, "blockMachinePort_Fluid")
    GameRegistry.registerBlock(machineModule_Coil, "blockMachinePort_Coil")
    GameRegistry.registerBlock(machineModule_Smelter, "blockMachinePort_Smelter")
  }

  def registerTileEntities() {
    GameRegistry.registerTileEntity(classOf[MachineControllerEntity], Settings.namespace + "machineController")
    GameRegistry.registerTileEntity(classOf[MachinePortItems], Settings.namespace + "machinePortItems")
    GameRegistry.registerTileEntity(classOf[MachinePortPower], Settings.namespace + "machinePortPower")
    GameRegistry.registerTileEntity(classOf[MachinePortFluid], Settings.namespace + "machinePortFluid")
    GameRegistry.registerTileEntity(classOf[MachineModuleSmelter], Settings.namespace + "machineModuleSmelter")
    GameRegistry.registerTileEntity(classOf[MachineModuleCoil], Settings.namespace + "machineModuleCoil")
  }

  registerCallback(1, registerBlocks)
  registerCallback(2, registerTileEntities)
}
