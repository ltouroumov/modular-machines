package ch.ltouroumov.modularmachines.common.tileentity.ports

import ch.ltouroumov.modularmachines.common.tileentity.PortType
import ch.ltouroumov.modularmachines.common.tileentity.utils.{DiagnosableEntity, SidedContainerEntity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.common.util.ForgeDirection

class MachinePortItems extends MachinePortBase with SidedContainerEntity with DiagnosableEntity {
  def portType = PortType.Items


  // Returns machine diagnostic informations
  override def onDiagnose(message: StringBuilder, player: EntityPlayer, x: Int, y: Int, z: Int, side: Int): Unit = {
    message.append("Inventory Contents:")
    for ( stack <- slots if stack != null )
      message.append(s"  ${stack.stackSize}x ${stack.getItem.getUnlocalizedName}\n")
  }

  override def inventorySize: Int = 5
  override def isAccessibleSide(side: ForgeDirection): Boolean = isFront(side)
}
