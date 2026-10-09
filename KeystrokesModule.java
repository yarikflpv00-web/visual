package gg.donatil.visuals.modules.visual;

import gg.donatil.visuals.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/** Индикатор WASD: читаем локальные нажатия и рисуем рамки. */
public class KeystrokesModule extends Module {

    public KeystrokesModule() {
        super("Keystrokes", "WASD-индикатор нажатий", true);
    }

    @Override
    public void renderHud(DrawContext ctx, float tickDelta) {
        if (mc.player == null) return;
        long h = mc.getWindow().getHandle();
        int bx = 8;
        int by = ctx.getScaledWindowHeight() - 66;

        key(ctx, bx + 21, by,      "W", InputUtil.isKeyPressed(h, GLFW.GLFW_KEY_W));
        key(ctx, bx,      by + 21, "A", InputUtil.isKeyPressed(h, GLFW.GLFW_KEY_A));
        key(ctx, bx + 21, by + 21, "S", InputUtil.isKeyPressed(h, GLFW.GLFW_KEY_S));
        key(ctx, bx + 42, by + 21, "D", InputUtil.isKeyPressed(h, GLFW.GLFW_KEY_D));
    }

    private void key(DrawContext ctx, int x, int y, String label, boolean pressed) {
        ctx.fill(x, y, x + 19, y + 19, pressed ? 0xCC9D6BFF : 0x660B0916);
        ctx.drawBorder(x, y, 19, 19, 0x55FFFFFF);
        ctx.drawCenteredTextWithShadow(mc.textRenderer, label,
                x + 9, y + 6, pressed ? 0xFF0B0916 : 0xFFC4A8FF);
    }
}
