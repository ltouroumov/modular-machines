package ch.ltouroumov.modularmachines.common.tileentity

import ch.ltouroumov.modularmachines.common.tileentity.utils.{WrenchableEntity, RotatableEntity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class MachineControllerEntity extends BaseEntity with RotatableEntity with WrenchableEntity {

  override def updateEntity(): Unit = {
    // Perform processing calculations
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }

}
