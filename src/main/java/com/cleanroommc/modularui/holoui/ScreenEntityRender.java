package com.cleanroommc.modularui.holoui;

import com.cleanroommc.modularui.screen.GuiScreenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

/**
 * Highly experimental
 */
@ApiStatus.Experimental
public class ScreenEntityRender extends Render {

    public ScreenEntityRender() {}

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }

    @Override
    public void doRender(@NotNull Entity e, double x, double y, double z, float entityYaw, float partialTicks) {
        HoloScreenEntity entity = (HoloScreenEntity) e;
        GuiScreenWrapper screenWrapper = entity.getWrapper();
        if (screenWrapper == null) return;

        Plane3D plane3D = entity.getPlane3D();
        if (entity.getOrientation() == ScreenOrientation.TO_PLAYER) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            float xN = (float) (player.posX - entity.posX);
            float yN = (float) (player.posY - entity.posY);
            float zN = (float) (player.posZ - entity.posZ);
            plane3D.setNormal(xN, yN, zN);
        }
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        plane3D.transformRectangle();
        screenWrapper.drawScreen(0, 0, partialTicks);
        GL11.glPopMatrix();
    }
}
