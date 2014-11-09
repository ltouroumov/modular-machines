package ch.ltouroumov.modularmachines.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.{NetworkManager, Packet}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * Created by Jeremy David on 09.11.2014.
 */
class BaseEntity extends TileEntity {
  override def getDescriptionPacket : Packet = {
    val tag = new NBTTagCompound()
    writeToNBT(tag)
    new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag)
  }

  override def onDataPacket(net: NetworkManager, packet: S35PacketUpdateTileEntity) = {
    readFromNBT(packet.func_148857_g())
  }
}
