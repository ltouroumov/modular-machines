package ch.ltouroumov.modularmachines

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import ch.ltouroumov.modularmachines.common._

@Mod(modid = "modularmachines", name = "Modular Machines", version = "0.1", modLanguage = "scala")
object ModularMachines {

  @SidedProxy(clientSide = "ch.ltouroumov.modularmachines.client.ClientProxy",
              serverSide = "ch.ltouroumov.modularmachines.server.ServerProxy")
  var proxy: CommonProxy = null

  val tabModularMachines = new CreativeTabs("ModularMachines") {
    override def getTabIconItem: Item = Items.coal
  }

  @EventHandler
  def preInit(evt: FMLPreInitializationEvent) =
    proxy.preInit(evt)

  @EventHandler
  def init(evt: FMLInitializationEvent) =
    proxy.onInit(evt)

  @EventHandler
  def postInit(evt: FMLPostInitializationEvent) =
    proxy.postInit(evt)
}