package ch.ltouroumov.modularmachines.common.tileentity.utils

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait DiagnosableEntity {

  // Returns machine diagnostic informations
  def onDiagnose(message: StringBuilder, player: EntityPlayer, x: Int, y: Int, z:Int, side: Int)

}
