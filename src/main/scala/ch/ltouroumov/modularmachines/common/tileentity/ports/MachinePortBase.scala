package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.PortType._
import ch.ltouroumov.modularmachines.common.tileentity.utils.{WrenchableEntity, RotatableEntity}
import ch.ltouroumov.modularmachines.common.tileentity.{BaseEntity, PortDirection}
import ch.ltouroumov.modularmachines.common.utils.WorldUpdate
import ch.ltouroumov.modularmachines.utils.{EntitySaveHandler, EntityLoadHandler}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.Facing
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

abstract class MachinePortBase extends BaseEntity with RotatableEntity with WrenchableEntity {
  def portType: PortType
  var portDirection = PortDirection.In

  @EntitySaveHandler
  def writeToWorld(tag: NBTTagCompound): Unit = {
    tag.setInteger("Type", portType.id)
    tag.setInteger("Direction", portDirection.id)
  }

  @EntityLoadHandler
  def readFromWorld(tag: NBTTagCompound): Unit = {
    portDirection = PortDirection(tag.getInteger("Direction"))
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    val worldUpdate = WorldUpdate(world)
    if (player.isSneaking)
      portDirection = PortDirection.reverse(portDirection)
    else {
      rotate(ForgeDirection.UP)
      worldUpdate.onSide(x, y, z, oldFacing)
      worldUpdate.onSide(x, y, z, facing)
    }
    worldUpdate.atCoords(x, y, z)
  }

}