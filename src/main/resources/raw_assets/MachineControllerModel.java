// Date: 12.11.2014 22:48:23
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package net.minecraft.src;

public class ModelMachineControllerModel extends ModelBase
{
  //fields
    ModelRenderer LeftSide;
    ModelRenderer RightSide;
    ModelRenderer BackSide;
    ModelRenderer BottomSide;
    ModelRenderer TopSide;
    ModelRenderer Separator;
    ModelRenderer Slot1;
    ModelRenderer Slot2;
    ModelRenderer Slot3;
    ModelRenderer Slot4;
    ModelRenderer Card1;
    ModelRenderer Card2;
    ModelRenderer Card3;
    ModelRenderer Card4;
  
  public ModelMachineControllerModel()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      LeftSide.mirror = true;
      LeftSide = new ModelRenderer(this, 0, 0);
      LeftSide.addBox(0F, 0F, 0F, 1, 9, 16);
      LeftSide.setRotationPoint(-8F, 7F, -8F);
      LeftSide.setTextureSize(128, 64);
      LeftSide.mirror = true;
      setRotation(LeftSide, 0F, 0F, 0F);
      LeftSide.mirror = false;
      RightSide = new ModelRenderer(this, 0, 0);
      RightSide.addBox(-1F, 0F, 0F, 1, 9, 16);
      RightSide.setRotationPoint(8F, 7F, -8F);
      RightSide.setTextureSize(128, 64);
      RightSide.mirror = true;
      setRotation(RightSide, 0F, 0F, 0F);
      BackSide = new ModelRenderer(this, 34, 15);
      BackSide.addBox(0F, 0F, 0F, 14, 9, 1);
      BackSide.setRotationPoint(-7F, 7F, 7F);
      BackSide.setTextureSize(128, 64);
      BackSide.mirror = true;
      setRotation(BackSide, 0F, 0F, 0F);
      BottomSide = new ModelRenderer(this, 0, 25);
      BottomSide.addBox(0F, 0F, 0F, 14, 1, 15);
      BottomSide.setRotationPoint(-7F, 15F, -8F);
      BottomSide.setTextureSize(128, 64);
      BottomSide.mirror = true;
      setRotation(BottomSide, 0F, 0F, 0F);
      TopSide = new ModelRenderer(this, 0, 41);
      TopSide.addBox(0F, 0F, 0F, 16, 7, 16);
      TopSide.setRotationPoint(-8F, 0F, -8F);
      TopSide.setTextureSize(128, 64);
      TopSide.mirror = true;
      setRotation(TopSide, 0F, 0F, 0F);
      Separator = new ModelRenderer(this, 36, -6);
      Separator.addBox(0F, 0F, 0F, 1, 8, 13);
      Separator.setRotationPoint(-1F, 7F, -6F);
      Separator.setTextureSize(128, 64);
      Separator.mirror = true;
      setRotation(Separator, 0F, 0F, 0F);
      Slot1 = new ModelRenderer(this, 0, 0);
      Slot1.addBox(0F, 0F, 0F, 1, 6, 1);
      Slot1.setRotationPoint(5F, 8F, 6F);
      Slot1.setTextureSize(128, 64);
      Slot1.mirror = true;
      setRotation(Slot1, 0F, 0F, 0F);
      Slot2 = new ModelRenderer(this, 0, 0);
      Slot2.addBox(0F, 0F, 0F, 1, 6, 1);
      Slot2.setRotationPoint(3F, 8F, 6F);
      Slot2.setTextureSize(128, 64);
      Slot2.mirror = true;
      setRotation(Slot2, 0F, 0F, 0F);
      Slot3 = new ModelRenderer(this, 0, 0);
      Slot3.addBox(0F, 0F, 0F, 1, 6, 1);
      Slot3.setRotationPoint(1F, 8F, 6F);
      Slot3.setTextureSize(128, 64);
      Slot3.mirror = true;
      setRotation(Slot3, 0F, 0F, 0F);
      Slot4 = new ModelRenderer(this, 0, 0);
      Slot4.addBox(0F, 0F, 0F, 4, 6, 1);
      Slot4.setRotationPoint(-6F, 8F, 5F);
      Slot4.setTextureSize(128, 64);
      Slot4.mirror = true;
      setRotation(Slot4, 0F, 0F, 0F);
      Card1 = new ModelRenderer(this, 64, 0);
      Card1.addBox(0F, 0F, 0F, 1, 6, 12);
      Card1.setRotationPoint(5F, 8F, -6F);
      Card1.setTextureSize(128, 64);
      Card1.mirror = true;
      setRotation(Card1, 0F, 0F, 0F);
      Card2 = new ModelRenderer(this, 64, 0);
      Card2.addBox(0F, 0F, 0F, 1, 6, 12);
      Card2.setRotationPoint(3F, 8F, -6F);
      Card2.setTextureSize(128, 64);
      Card2.mirror = true;
      setRotation(Card2, 0F, 0F, 0F);
      Card3 = new ModelRenderer(this, 64, 0);
      Card3.addBox(0F, 0F, 0F, 1, 6, 12);
      Card3.setRotationPoint(1F, 8F, -6F);
      Card3.setTextureSize(128, 64);
      Card3.mirror = true;
      setRotation(Card3, 0F, 0F, 0F);
      Card4 = new ModelRenderer(this, 64, 18);
      Card4.addBox(0F, 0F, 0F, 4, 6, 12);
      Card4.setRotationPoint(-6F, 8F, -6F);
      Card4.setTextureSize(128, 64);
      Card4.mirror = true;
      setRotation(Card4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    LeftSide.render(f5);
    RightSide.render(f5);
    BackSide.render(f5);
    BottomSide.render(f5);
    TopSide.render(f5);
    Separator.render(f5);
    Slot1.render(f5);
    Slot2.render(f5);
    Slot3.render(f5);
    Slot4.render(f5);
    Card1.render(f5);
    Card2.render(f5);
    Card3.render(f5);
    Card4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
