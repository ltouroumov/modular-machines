package ch.ltouroumov.modularmachines.common.tileentity.utils

import ch.ltouroumov.modularmachines.utils.annotation.{EntitySaveHandler, EntityLoadHandler}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import java.lang.reflect.Method

class SaveHandler(val entityKlass: Class[_]) {
  private val saveHandlers = getHandlerMethods(handler = SaveHandler)
  private val loadHandlers = getHandlerMethods(handler = LoadHandler)

  def writeToNBT(self: TileEntity, tag: NBTTagCompound): Unit = {
    for (handler <- saveHandlers)
      handler.invoke(self, tag)
  }

  def readFromNBT(self: TileEntity, tag: NBTTagCompound): Unit = {
    for (handler <- loadHandlers)
      handler.invoke(self, tag)
  }

  private def getHandlerMethods(handler: HandlerType): Array[Method] = {
    val handlerKlass = handler match {
      case SaveHandler => classOf[EntitySaveHandler]
      case LoadHandler => classOf[EntityLoadHandler]
    }

    val allMethods = entityKlass.getMethods
    val annotatedMethods = allMethods.filter(_ isAnnotationPresent handlerKlass)
    annotatedMethods
  }

  trait HandlerType
  case object SaveHandler extends HandlerType
  case object LoadHandler extends HandlerType
}

object SaveHandlerCache {

  var handlerCache: Map[Class[_], SaveHandler] = Map.empty

  def fetch(entity: TileEntity): SaveHandler = {
    val klass = entity.getClass
    handlerCache.get(klass) match {
      case Some(cache) =>
        cache
      case None =>
        updateHandlerCache(klass)
    }
  }


  private def updateHandlerCache(klass: Class[_]): SaveHandler = {
    val handler = new SaveHandler(klass)
    handlerCache += ( klass -> handler )
    handler
  }

}
