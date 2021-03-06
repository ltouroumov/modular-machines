package ch.ltouroumov.modularmachines.common.utils

import net.minecraft.client.renderer.OpenGlHelper
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl._
import org.lwjgl.util.glu.GLU

/**
 * From OpenComputers RenderState class
 * https://github.com/MightyPirates/OpenComputers/blob/223f981ae63fef0622e9d6e6eb74bc43997ae3ba/src/main/scala/li/cil/oc/util/RenderState.scala
 */
object RenderUtils {
  val arb = GLContext.getCapabilities.GL_ARB_multitexture && !GLContext.getCapabilities.OpenGL13

  private val canUseBlendColor = GLContext.getCapabilities.OpenGL14

  def angleFromDirection(dir: ForgeDirection): Float = {
    dir match {
      case ForgeDirection.NORTH => 0
      case ForgeDirection.EAST  => 90
      case ForgeDirection.SOUTH => 180
      case ForgeDirection.WEST  => 270
      case _ => 0
    }
  }

  def checkError(where: String) {
    val error = GL11.glGetError
    if (error != 0) {
      System.out.println("GL ERROR @ " + where + ": " + GLU.gluErrorString(error))
    }
  }

  def compilingDisplayList = {
    if (GL11.glGetInteger(GL11.GL_LIST_INDEX) != 0) {
      val mode = GL11.glGetInteger(GL11.GL_LIST_MODE)
      mode == GL11.GL_COMPILE || mode == GL11.GL_COMPILE_AND_EXECUTE
    }
    else false
  }

  def disableLighting() {
    GL11.glDisable(GL11.GL_LIGHTING)
    if (arb) {
      ARBMultitexture.glActiveTextureARB(OpenGlHelper.lightmapTexUnit)
      GL11.glDisable(GL11.GL_TEXTURE_2D)
      ARBMultitexture.glActiveTextureARB(OpenGlHelper.defaultTexUnit)
    }
    else {
      GL13.glActiveTexture(OpenGlHelper.lightmapTexUnit)
      GL11.glDisable(GL11.GL_TEXTURE_2D)
      GL13.glActiveTexture(OpenGlHelper.defaultTexUnit)
    }
  }

  def enableLighting() {
    GL11.glEnable(GL11.GL_LIGHTING)
    if (arb) {
      ARBMultitexture.glActiveTextureARB(OpenGlHelper.lightmapTexUnit)
      GL11.glEnable(GL11.GL_TEXTURE_2D)
      ARBMultitexture.glActiveTextureARB(OpenGlHelper.defaultTexUnit)
    }
    else {
      GL13.glActiveTexture(OpenGlHelper.lightmapTexUnit)
      GL11.glEnable(GL11.GL_TEXTURE_2D)
      GL13.glActiveTexture(OpenGlHelper.defaultTexUnit)
    }
  }

  def makeItBlend() {
    GL11.glEnable(GL11.GL_BLEND)
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    GL11.glDepthFunc(GL11.GL_LEQUAL)
  }

  def setBlendAlpha(alpha: Float) = if (canUseBlendColor) {
    GL14.glBlendColor(0, 0, 0, alpha)
    GL11.glBlendFunc(GL11.GL_CONSTANT_ALPHA, GL11.GL_ONE)
  }
}