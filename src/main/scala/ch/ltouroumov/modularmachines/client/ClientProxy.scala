package ch.ltouroumov.modularmachines.client

import ch.ltouroumov.modularmachines.client.models.{MachineProgramRender, MachineControllerRender}
import ch.ltouroumov.modularmachines.common.CommonProxy
import ch.ltouroumov.modularmachines.common.init.Items
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import cpw.mods.fml.client.registry.ClientRegistry
import net.minecraftforge.client.MinecraftForgeClient

class ClientProxy extends CommonProxy {
  override def registerRenders(): Unit = {
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[MachineControllerEntity], new MachineControllerRender)
    MinecraftForgeClient.registerItemRenderer(Items.machineProgram, new MachineProgramRender)
  }
}
