package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.PortType._
import ch.ltouroumov.modularmachines.common.tileentity.{BaseEntity, PortDirection, RotatableEntity, WrenchableEntity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

abstract class MachinePortBase extends BaseEntity with RotatableEntity with WrenchableEntity {
  def portType: PortType
  var portDirection = PortDirection.In

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    saveFacing(tag)
    tag.setInteger("Type", portType.id)
    tag.setInteger("Direction", portDirection.id)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    readFacing(tag)
    portDirection = PortDirection(tag.getInteger("Direction"))
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    if (player.isSneaking)
      portDirection = PortDirection.reverse(portDirection)
    else
      rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }

}