package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import ch.ltouroumov.modularmachines.common.tileentity.PortType._

class MachinePortEntity(pType: PortType) extends BaseEntity with RotatableEntity with WrenchableEntity {
  var portType = pType
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
    portType = PortType(tag.getInteger("Type"))
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