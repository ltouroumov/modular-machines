package ch.ltouroumov.modularmachines.common.items

import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.tileentity.utils.DiagnosableEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World

class Multimeter extends Item {
  setMaxStackSize(1)
  setCreativeTab(ModularMachines.tabModularMachines)
  setUnlocalizedName("Multimeter")
  setTextureName("modularmachines:Multimeter")

  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int, p2: Float, p3: Float, p4: Float) : Boolean =
    if (!world.isRemote)
      world.getTileEntity(x, y, z) match {
        case wr: DiagnosableEntity =>
          val message = new StringBuilder
          wr.onDiagnose(message, player, x, y, z, side)
          player.addChatComponentMessage(new ChatComponentText(message.toString()))
          true
        case _ =>
          false
      }
    else false
}
