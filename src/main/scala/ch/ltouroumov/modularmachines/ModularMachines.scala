package ch.ltouroumov.modularmachines

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item
import ch.ltouroumov.modularmachines.common._

@Mod(modid = "modularmachines", name = "Modular Machines", version = "0.1", modLanguage = "scala")
object ModularMachines {


  @SidedProxy(clientSide = "ch.ltouroumov.modularmachines.client.ClientProxy",
              serverSide = "ch.ltouroumov.modularmachines.server.ServerProxy")
  var proxy: CommonProxy = null

  val tabModularMachines = new CreativeTabs("modularMachines") {
    override def getTabIconItem: Item = Items.ender_eye
  }

  val blockMachineFrame = new blocks.MachineFrame
  var blockMachineController = new blocks.MachineController
  var blockMachineGlass = new blocks.MachineGlass
  val blockMachinePort = new blocks.MachinePort
  val itemWrench = new items.Wrench

  @EventHandler
  def preInit(evt: FMLPreInitializationEvent) {
    // Pre init
  }

  @EventHandler
  def init(evt: FMLInitializationEvent) {
    // init
    GameRegistry.registerBlock(blockMachineFrame, "blockMachineFrame")
    GameRegistry.registerBlock(blockMachineController, "blockMachineController")
    GameRegistry.registerBlock(blockMachineGlass, "blockMachineGlass")
    GameRegistry.registerBlock(blockMachinePort, "blockMachinePort")
    GameRegistry.registerTileEntity(classOf[tileentity.MachineControllerEntity], "machineController")
    GameRegistry.registerItem(itemWrench, "itemWrench")
  }

  @EventHandler
  def postInit(evt: FMLPostInitializationEvent) {
    // Post init
  }

}