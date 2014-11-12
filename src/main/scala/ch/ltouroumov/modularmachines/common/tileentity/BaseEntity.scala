package ch.ltouroumov.modularmachines.common.tileentity

import ch.ltouroumov.modularmachines.common.tileentity.utils.SaveHandlerCache
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.{NetworkManager, Packet}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

class BaseEntity extends TileEntity {
  val saveHandler = SaveHandlerCache.fetch(this)

  override def writeToNBT(tag: NBTTagCompound): Unit = {
    super.writeToNBT(tag)
    saveHandler.writeToNBT(this, tag)
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    saveHandler.readFromNBT(this, tag)
  }

  override def getDescriptionPacket : Packet = {
    val tag = new NBTTagCompound()
    writeToNBT(tag)
    new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag)
  }

  override def onDataPacket(net: NetworkManager, packet: S35PacketUpdateTileEntity) = {
    readFromNBT(packet.func_148857_g())
  }
}
