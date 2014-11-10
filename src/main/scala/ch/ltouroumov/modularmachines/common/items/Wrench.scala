package ch.ltouroumov.modularmachines.common.items

import ch.ltouroumov.modularmachines.ModularMachines
import ch.ltouroumov.modularmachines.common.tileentity._
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class Wrench extends Item {
  setMaxStackSize(1)
  setCreativeTab(ModularMachines.tabModularMachines)
  setUnlocalizedName("Wrench")
  setTextureName("modularmachines:Wrench")

  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int, p2: Float, p3: Float, p4: Float) : Boolean =
    world.getTileEntity(x, y, z) match {
      case wr: WrenchableEntity =>
        wr.onWrench(stack, player, world, x, y, z, side)
        true
      case _ =>
        false
    }
}
