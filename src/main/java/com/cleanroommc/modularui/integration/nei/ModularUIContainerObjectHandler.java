package com.cleanroommc.modularui.integration.nei;

import codechicken.nei.guihook.IContainerObjectHandler;
import com.cleanroommc.modularui.api.widget.IGuiElement;
import com.cleanroommc.modularui.screen.GuiScreenWrapper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public class ModularUIContainerObjectHandler implements IContainerObjectHandler {

    @Override
    public void guiTick(GuiContainer gui) {}

    @Override
    public void refresh(GuiContainer gui) {}

    @Override
    public void load(GuiContainer gui) {}

    @Override
    public ItemStack getStackUnderMouse(GuiContainer gui, int mousex, int mousey) {
        if (gui instanceof GuiScreenWrapper) {
            IGuiElement hovered = ((GuiScreenWrapper) gui).getScreen().context.getHovered();
            if (hovered instanceof IHasStackUnderMouse) {
                return ((IHasStackUnderMouse) hovered).getStackUnderMouse();
            }
        }
        return null;
    }

    @Override
    public boolean objectUnderMouse(GuiContainer gui, int mousex, int mousey) {
        return false;
    }

    @Override
    public boolean shouldShowTooltip(GuiContainer gui) {
        return true;
    }
}
