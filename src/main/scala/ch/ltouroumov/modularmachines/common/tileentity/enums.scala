package ch.ltouroumov.modularmachines.common.tileentity

object ModuleType extends Enumeration {
  type ModuleType = Value

  val Smelter = Value("Smelter")
  val Coil = Value("Coil")
}

object PortType extends Enumeration {
  type PortType = Value
  val Items = Value("Items")
  val Power = Value("Power")
  val Fluid = Value("Fluid")
}

object PortDirection extends Enumeration {
  type PortDirection = Value
  val In = Value("In")
  val Out = Value("Out")

  def reverse(direction: PortDirection) =
    direction match {
      case In => Out
      case Out => In
    }
}

object ControllerModule {
  trait ControllerCardType

  case class EnergyCard(tier: Int) extends ControllerCardType
  case class BufferCard(tier: Int) extends ControllerCardType
  case class SpeedCard(tier: Int) extends ControllerCardType

  trait ControllerProgramType

  case object SmelterProgram extends ControllerProgramType
}