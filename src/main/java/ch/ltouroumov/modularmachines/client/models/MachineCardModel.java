// Date: 14.11.2014 20:02:37
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ch.ltouroumov.modularmachines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MachineCardModel extends ModelBase {
    //fields
    ModelRenderer CardBase;
    ModelRenderer Component1;
    ModelRenderer Component2;
    ModelRenderer Component3;
    ModelRenderer Component4;
    ModelRenderer Component5;
    ModelRenderer Component6;
    ModelRenderer Component7;
    ModelRenderer Component8;
    ModelRenderer Component9;
    ModelRenderer Component10;
    ModelRenderer Component11;
    ModelRenderer Component12;
    ModelRenderer Component13;
    ModelRenderer Component14;

    public MachineCardModel() {
        textureWidth = 64;
        textureHeight = 64;

        CardBase = new ModelRenderer(this, 0, 0);
        CardBase.addBox(0F, 0F, 0F, 1, 6, 12);
        CardBase.setRotationPoint(0F, 0F, 0F);
        CardBase.setTextureSize(64, 64);
        CardBase.mirror = true;
        setRotation(CardBase, 0F, 0F, 0F);
        Component1 = new ModelRenderer(this, 20, 0);
        Component1.addBox(0F, 0F, 0F, 1, 2, 2);
        Component1.setRotationPoint(-0.4F, 1F, 1F);
        Component1.setTextureSize(64, 64);
        Component1.mirror = true;
        setRotation(Component1, 0F, 0F, 0F);
        Component2 = new ModelRenderer(this, 0, 4);
        Component2.addBox(0F, 0F, 0F, 1, 2, 4);
        Component2.setRotationPoint(-0.6F, 1F, 4F);
        Component2.setTextureSize(64, 64);
        Component2.mirror = true;
        setRotation(Component2, 0F, 0F, 0F);
        Component3 = new ModelRenderer(this, 14, 0);
        Component3.addBox(0F, 0F, 0F, 1, 2, 2);
        Component3.setRotationPoint(-0.4F, 1F, 9F);
        Component3.setTextureSize(64, 64);
        Component3.mirror = true;
        setRotation(Component3, 0F, 0F, 0F);
        Component4 = new ModelRenderer(this, 0, 0);
        Component4.addBox(0F, 0F, 0F, 1, 1, 2);
        Component4.setRotationPoint(-0.4F, 4F, 1F);
        Component4.setTextureSize(64, 64);
        Component4.mirror = true;
        setRotation(Component4, 0F, 0F, 0F);
        Component5 = new ModelRenderer(this, 0, 0);
        Component5.addBox(0F, 0F, 0F, 1, 1, 2);
        Component5.setRotationPoint(-0.4F, 4F, 4F);
        Component5.setTextureSize(64, 64);
        Component5.mirror = true;
        setRotation(Component5, 0F, 0F, 0F);
        Component6 = new ModelRenderer(this, 0, 4);
        Component6.addBox(0F, 0F, 0F, 1, 1, 4);
        Component6.setRotationPoint(-0.6F, 4F, 7F);
        Component6.setTextureSize(64, 64);
        Component6.mirror = true;
        setRotation(Component6, 0F, 0F, 0F);
        Component7 = new ModelRenderer(this, 0, 4);
        Component7.addBox(0F, 0F, 0F, 1, 2, 1);
        Component7.setRotationPoint(0.6F, 1F, 1F);
        Component7.setTextureSize(64, 64);
        Component7.mirror = true;
        setRotation(Component7, 0F, 0F, 0F);
        Component8 = new ModelRenderer(this, 0, 4);
        Component8.addBox(0F, 0F, 0F, 1, 2, 1);
        Component8.setRotationPoint(0.6F, 1F, 3F);
        Component8.setTextureSize(64, 64);
        Component8.mirror = true;
        setRotation(Component8, 0F, 0F, 0F);
        Component9 = new ModelRenderer(this, 0, 4);
        Component9.addBox(0F, 0F, 0F, 1, 2, 1);
        Component9.setRotationPoint(0.6F, 1F, 5F);
        Component9.setTextureSize(64, 64);
        Component9.mirror = true;
        setRotation(Component9, 0F, 0F, 0F);
        Component10 = new ModelRenderer(this, 0, 4);
        Component10.addBox(0F, 0F, 0F, 1, 2, 1);
        Component10.setRotationPoint(0.6F, 1F, 7F);
        Component10.setTextureSize(64, 64);
        Component10.mirror = true;
        setRotation(Component10, 0F, 0F, 0F);
        Component11 = new ModelRenderer(this, 20, 0);
        Component11.addBox(0F, 0F, 0F, 1, 2, 2);
        Component11.setRotationPoint(0.4F, 1F, 9F);
        Component11.setTextureSize(64, 64);
        Component11.mirror = true;
        setRotation(Component11, 0F, 0F, 0F);
        Component12 = new ModelRenderer(this, 0, 0);
        Component12.addBox(0F, 0F, 0F, 1, 1, 3);
        Component12.setRotationPoint(0.4F, 4F, 1F);
        Component12.setTextureSize(64, 64);
        Component12.mirror = true;
        setRotation(Component12, 0F, 0F, 0F);
        Component13 = new ModelRenderer(this, 0, 0);
        Component13.addBox(0F, 0F, 0F, 1, 1, 2);
        Component13.setRotationPoint(0.4F, 4F, 5F);
        Component13.setTextureSize(64, 64);
        Component13.mirror = true;
        setRotation(Component13, 0F, 0F, 0F);
        Component14 = new ModelRenderer(this, 0, 0);
        Component14.addBox(0F, 0F, 0F, 1, 1, 3);
        Component14.setRotationPoint(0.4F, 4F, 8F);
        Component14.setTextureSize(64, 64);
        Component14.mirror = true;
        setRotation(Component14, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        CardBase.render(f5);
        Component1.render(f5);
        Component2.render(f5);
        Component3.render(f5);
        Component4.render(f5);
        Component5.render(f5);
        Component6.render(f5);
        Component7.render(f5);
        Component8.render(f5);
        Component9.render(f5);
        Component10.render(f5);
        Component11.render(f5);
        Component12.render(f5);
        Component13.render(f5);
        Component14.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
