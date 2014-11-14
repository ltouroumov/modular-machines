package ch.ltouroumov.modularmachines.client.models

import ch.ltouroumov.modularmachines.Settings
import ch.ltouroumov.modularmachines.common.tileentity.controller.MachineControllerEntity
import ch.ltouroumov.modularmachines.common.utils.RenderUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import org.lwjgl.opengl.GL11

class MachineProgramRender extends IItemRenderer {
  val itemRender = new RenderItem
  val model = new MachineProgramModel

  override def handleRenderType(item: ItemStack, renderType: ItemRenderType): Boolean = {
    renderType match {
      case ItemRenderType.INVENTORY | ItemRenderType.ENTITY =>
        true
      case _ =>
        false
    }
  }

  override def shouldUseRenderHelper(renderType: ItemRenderType, item: ItemStack, helper: ItemRendererHelper): Boolean = {
    renderType match {
      case ItemRenderType.ENTITY =>
        helper == ItemRendererHelper.ENTITY_BOBBING ||
        helper == ItemRendererHelper.ENTITY_ROTATION
      case ItemRenderType.INVENTORY =>
        helper == ItemRendererHelper.BLOCK_3D
      case _ =>
        false
    }
  }

  override def renderItem(renderType: ItemRenderType, item: ItemStack, data: AnyRef*): Unit = {
    RenderUtils.checkError("Before MachineControllerRender")

    GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
    GL11.glPushMatrix()

    renderType match {
      case ItemRenderType.ENTITY =>
        GL11.glTranslatef(0, 0.5f, -0.5f)
        GL11.glRotatef(180, 1, 0, 0)
        GL11.glScalef(0.25f, 0.25f, 0.25f)
      case ItemRenderType.INVENTORY =>
        GL11.glTranslatef(2f, 6f, 0)
        GL11.glScalef(1, -1, 1)
      case _ =>
        GL11.glScalef(1, 1, 1)
    }

    Minecraft
      .getMinecraft
      .getTextureManager
      .bindTexture(new ResourceLocation(Settings.modid, "textures/items/Program_Map.png"))
    model.render(null, 0, 0, 0, 0, 0, 2f)

    GL11.glPopMatrix()
    GL11.glPopAttrib()

    RenderUtils.checkError("After MachineControllerRender")
  }
}
