package ch.ltouroumov.modularmachines.common.utils

import net.minecraft.nbt.{NBTTagCompound, NBTBase, NBTTagList}

class NBTTagListIterator[T <: NBTBase](val tagList: NBTTagList, val itemFetch: (Int, NBTTagList) => T) extends Iterator[(Int, T)] {
  val listSize = tagList.tagCount()
  var counter = -1

  def hasNext: Boolean = counter < listSize
  def next: (Int, T) = {
    counter += 1
    (counter, itemFetch(counter, tagList))
  }
}

object NBTTagListIterator {

  def compoundIterator(tagList: NBTTagList) = {
    new NBTTagListIterator[NBTTagCompound](tagList, { (index, list) => list.getCompoundTagAt(index) })
  }

}