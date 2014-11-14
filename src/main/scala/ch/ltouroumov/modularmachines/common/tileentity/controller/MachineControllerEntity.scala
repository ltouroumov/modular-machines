package ch.ltouroumov.modularmachines.common.tileentity.controller

import ch.ltouroumov.modularmachines.common.logic.{MachineCardFactory, MachineProgramFactory}
import ch.ltouroumov.modularmachines.common.logic.card.MachineCardBase
import ch.ltouroumov.modularmachines.common.logic.program.MachineProgramBase
import ch.ltouroumov.modularmachines.common.tileentity.BaseEntity
import ch.ltouroumov.modularmachines.common.tileentity.utils.{RotatableEntity, WrenchableEntity}
import ch.ltouroumov.modularmachines.common.utils.NBTTagListIterator
import ch.ltouroumov.modularmachines.utils.annotation.{MachineProgram, EntitySaveHandler, EntityLoadHandler}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagList, NBTTagCompound}
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants.NBT
import net.minecraftforge.common.util.ForgeDirection

class MachineControllerEntity extends BaseEntity with RotatableEntity with WrenchableEntity {

  val CARD_SLOTS = 3

  var programSlot: MachineProgramBase = null
  val cardSlots = new Array[MachineCardBase](CARD_SLOTS)

  def insertCard(card: MachineCardBase): Boolean = {
    //for (slot <- cardSlots. if slot == null)
    false
  }

  @EntitySaveHandler
  def saveCards(tag: NBTTagCompound): Unit = {
    if (programSlot != null) {
      tag.setString("ProgramName", programSlot.name)
      val programData = new NBTTagCompound()
      programSlot.saveData(programData)
      tag.setTag("ProgramData", programData)
    }
    val slotsData = new NBTTagList()
    for (slot <- cardSlots if slot != null) {
      val slotData = new NBTTagCompound()
      slotData.setString("CardName", slot.name)
      slot.saveData(slotData)
      slotsData.appendTag(slotData)
    }
    tag.setTag("SlotsData", slotsData)
  }

  @EntityLoadHandler
  def loadCards(tag: NBTTagCompound): Unit = {
    if (tag.hasKey("ProgramName")) {
      val programName = tag.getString("ProgramName")
      val programData = tag.getCompoundTag("ProgramData")
      programSlot = MachineProgramFactory.create(programName)
      programSlot.loadData(programData)
    }

    if (!tag.hasKey("SlotsData"))
      return

    val slotsData = tag.getTagList("SlotsData", NBT.TAG_COMPOUND)
    for ((idx, slotData) <- NBTTagListIterator.compoundIterator(slotsData)) {
      val cardName = slotData.getString("CardName")
      val card = MachineCardFactory.create(cardName)
      if (card != null)
        card.loadData(slotData)
      cardSlots(idx) = card
    }
  }

  override def updateEntity(): Unit = {
    // Perform processing calculations
  }

  def onWrench(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z:Int, side: Int) = {
    rotate(ForgeDirection.UP)
    world.markBlockForUpdate(x, y, z)
  }

}
