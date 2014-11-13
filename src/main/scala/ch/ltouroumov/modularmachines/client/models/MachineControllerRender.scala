package ch.ltouroumov.modularmachines.client.models

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.init.Blocks
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import ch.ltouroumov.modularmachines.common.utils.RenderUtils
import net.minecraft.block.Block
import net.minecraft.client.renderer.{OpenGlHelper, Tessellator}
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl.GL11

class MachineControllerRender extends TileEntitySpecialRenderer {

  val model = new MachineControllerModel()

  override def renderTileEntityAt(entity: TileEntity, x: Double, y: Double, z: Double, f: Float): Unit = {
    RenderUtils.checkError("Before MachineControllerRender")

    GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)

    //RenderUtils.disableLighting()
    RenderUtils.makeItBlend()

    GL11.glPushMatrix()

    GL11.glTranslated(x, y, z)
    GL11.glTranslatef(0.5f, 1.0f, 0.5f)
    GL11.glRotatef(180, 1, 0, 0)
    GL11.glScalef(1, 1, 1)

    val te = entity.asInstanceOf[MachineControllerEntity]
    val angle = RenderUtils.angleFromDirection(te.facing)
    GL11.glRotated(angle, 0, 1, 0)

    model.card1Visible = te.cardSlots(0) != null
    model.card2Visible = te.cardSlots(1) != null
    model.card3Visible = te.cardSlots(2) != null
    model.card4Visible = te.programSlot != null

    bindTexture(new ResourceLocation(Settings.modid, "textures/blocks/Controller_Map.png"))
    model.render(null, 0, 0, -0.1f, 0, 0, 0.0625f)

    GL11.glPopMatrix()
    GL11.glPopAttrib()

    RenderUtils.checkError("After MachineControllerRender")
  }
}
