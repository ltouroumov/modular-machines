package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import ch.ltouroumov.modularmachines.common.tileentity.ModuleType._

class MachineModuleEntity(mType: ModuleType) extends BaseEntity with RotatableEntity with WrenchableEntity {
  var moduleType = mType

  override def writeToNBT(tag: NBTTagCompound) {
    super.writeToNBT(tag)
    saveFacing(tag)
    tag.setInteger("Type", moduleType.id)
  }

  override def readFromNBT(tag: NBTTagCompound) {
    super.readFromNBT(tag)
    readFacing(tag)
    moduleType = ModuleType(tag.getInteger("Type"))
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }
}
