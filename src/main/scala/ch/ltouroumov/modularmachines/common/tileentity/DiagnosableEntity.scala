package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait DiagnosableEntity {

  // Returns machine diagnostic informations
  def onDiagnose(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int): Map[String, String]

}
