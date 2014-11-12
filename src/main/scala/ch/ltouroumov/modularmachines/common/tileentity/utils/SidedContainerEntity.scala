package ch.ltouroumov.modularmachines.common.tileentity.utils

import ch.ltouroumov.modularmachines.common.utils.NBTTagListIterator
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagList, NBTTagCompound}
import net.minecraftforge.common.util.Constants.NBT
import net.minecraftforge.common.util.ForgeDirection

trait SidedContainerEntity extends ISidedInventory {
  var slots: Array[ItemStack] = new Array(inventorySize)

  def inventorySize: Int = 1
  def canInsert: Boolean = true
  def canExtract: Boolean = true

  def saveInventory(tag: NBTTagCompound) = {
    val itemsTag = new NBTTagList()
    for ( stack <- slots if stack != null ) {
      val stackTag = new NBTTagCompound()
      stack.writeToNBT(stackTag)
      itemsTag.appendTag(stackTag)
    }

    tag.setTag("Items", itemsTag)
  }

  def loadInventory(tag: NBTTagCompound) = {
    val itemsTag = tag.getTagList("Items", NBT.TAG_COMPOUND)
    for ( (idx, stackTag) <- NBTTagListIterator.compoundIterator(itemsTag) ) {
      slots(idx) = ItemStack.loadItemStackFromNBT(stackTag)
    }
  }

  def isAccessibleSide(side: ForgeDirection): Boolean
  
  override def getAccessibleSlotsFromSide(side : Int): Array[Int] =
    (0 until inventorySize).toArray

  override def canExtractItem(slot : Int, stack : ItemStack, side : Int): Boolean =
    isAccessibleSide(ForgeDirection.getOrientation(side)) && canExtract

  override def canInsertItem(slot : Int, stack : ItemStack, side : Int): Boolean =
    isAccessibleSide(ForgeDirection.getOrientation(side)) && canInsert

  override def decrStackSize(slot : Int, amount : Int): ItemStack = {
    val oldStack = slots(slot)
    val newStack = oldStack.copy()
    // Pick the smaller of the two
    newStack.stackSize = Math.min(amount, oldStack.stackSize)
    if (amount >= oldStack.stackSize) {
      slots(slot) = null
    } else {
      oldStack.stackSize -= amount
    }

    newStack
  }

  override def setInventorySlotContents(slot : Int, stack : ItemStack): Unit =
    slots(slot) = stack

  override def getStackInSlot(slot : Int): ItemStack =
    slots(slot)

  override def isUseableByPlayer(player : EntityPlayer): Boolean = true
  override def getSizeInventory: Int = inventorySize
  override def getInventoryStackLimit: Int = 64
  override def isItemValidForSlot(slot : Int, stack : ItemStack): Boolean = true
  override def getStackInSlotOnClosing(slot : Int): ItemStack =
    getStackInSlot(slot)
  override def hasCustomInventoryName: Boolean = false
  override def getInventoryName: String = "Container"
  //override def markDirty(): Unit = {}
  override def closeInventory(): Unit = {}
  override def openInventory(): Unit = {}
}
