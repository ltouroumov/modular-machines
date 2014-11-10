package ch.ltouroumov.modularmachines.common.tileentity.modules

import ch.ltouroumov.modularmachines.common.tileentity.ModuleType.ModuleType
import ch.ltouroumov.modularmachines.common.tileentity.{BaseEntity, RotatableEntity, WrenchableEntity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

abstract class MachineModuleBase extends BaseEntity with RotatableEntity with WrenchableEntity {
  def moduleType: ModuleType

  override def writeToNBT(tag: NBTTagCompound) {
    super.writeToNBT(tag)
    saveFacing(tag)
  }

  override def readFromNBT(tag: NBTTagCompound) {
    super.readFromNBT(tag)
    readFacing(tag)
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }
}
