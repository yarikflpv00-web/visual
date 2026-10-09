package gg.donatil.visuals.modules.visual;

import gg.donatil.visuals.modules.Module;
import net.minecraft.client.gui.DrawContext;

/** Счётчик FPS в углу экрана. */
public class FpsHudModule extends Module {

    public FpsHudModule() {
        super("FPS", "Счётчик кадров в углу", true);
    }

    @Override
    public void renderHud(DrawContext ctx, float tickDelta) {
        String text = mc.getCurrentFps() + " FPS";
        int w = mc.textRenderer.getWidth(text);
        ctx.fill(6, 6, 14 + w, 20, 0x660B0916);
        ctx.drawTextWithShadow(mc.textRenderer, text, 10, 10, 0xFF5CFFC0);
    }
}
