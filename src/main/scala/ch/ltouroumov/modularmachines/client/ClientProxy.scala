package ch.ltouroumov.modularmachines.client

import ch.ltouroumov.modularmachines.client.models.MachineControllerRender
import ch.ltouroumov.modularmachines.common.CommonProxy
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import cpw.mods.fml.client.registry.ClientRegistry

class ClientProxy extends CommonProxy {
  override def registerRenders(): Unit = {
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[MachineControllerEntity], new MachineControllerRender())
  }
}
