package ch.ltouroumov.modularmachines.common.init

trait ObjectRegistry {
  var callbacks = List.empty[(Int, () => Unit)]

  def registerCallback(order: Int, callback: () => Unit) = {
    callbacks = (order, callback) :: callbacks
  }

  def register() = {
    for ( (_, callback) <- callbacks.sortBy { case (order, _) => order } )
      callback()
  }
}
