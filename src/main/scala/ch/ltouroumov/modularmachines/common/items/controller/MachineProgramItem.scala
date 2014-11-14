package ch.ltouroumov.modularmachines.common.items.controller

import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.logic.MachineProgramFactory
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.world.World

class MachineProgramItem extends Item {
  setMaxStackSize(1)
  setCreativeTab(ModularMachines.tabModularMachines)
  setUnlocalizedName("MachineProgram")
  setTextureName("modularmachines:MachineProgram")



  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int, p2: Float, p3: Float, p4: Float) : Boolean =
    if (!world.isRemote)
      world.getTileEntity(x, y, z) match {
        case mc: MachineControllerEntity =>
          if (mc.programSlot == null)
            mc.programSlot = MachineProgramFactory.create(programName)
          true
        case _ =>
          false
      }
    else false
}
