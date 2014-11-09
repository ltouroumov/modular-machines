package ch.ltouroumov.modularmachines

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
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

  @EventHandler
  def preInit(evt: FMLPreInitializationEvent) {
    // Pre init
  }

  @EventHandler
  def init(evt: FMLInitializationEvent) {
    // init
    GameRegistry.registerBlock(blockMachineFrame, "blockMachineFrame")
    GameRegistry.registerBlock(blockMachineController, "blockMachineController")
    GameRegistry.registerTileEntity(classOf[tileentity.MachineControllerEntity], "machineController")
  }

  @EventHandler
  def postInit(evt: FMLPostInitializationEvent) {
    // Post init
  }

}