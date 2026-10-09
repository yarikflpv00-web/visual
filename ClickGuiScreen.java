package gg.donatil.visuals.ui;

import gg.donatil.visuals.DonatilVisuals;
import gg.donatil.visuals.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;

/**
 * Клик-GUI по правому шифту: список модулей, клик мышью — вкл/выкл.
 * Правый шифт открывает меню и закрывает его обратно.
 */
public class ClickGuiScreen extends Screen {

    private static final int PANEL_W  = 300;
    private static final int HEADER_H = 52;
    private static final int ROW_H    = 32;

    public ClickGuiScreen() {
        super(Text.literal("Donatil Visuals"));
    }

    private List<Module> modules() {
        return DonatilVisuals.MODULES.list();
    }

    private int panelHeight() {
        return HEADER_H + ROW_H * modules().size() + 8;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.render(ctx, mouseX, mouseY, delta); // фон и виджеты

        int x = (width - PANEL_W) / 2;
        int y = (height - panelHeight()) / 2;

        // панель
        ctx.fill(x, y, x + PANEL_W, y + panelHeight(), 0xF00B0916);
        ctx.drawBorder(x, y, PANEL_W, panelHeight(), 0xFF9D6BFF);

        // шапка
        ctx.fill(x, y, x + PANEL_W, y + HEADER_H, 0xFF1B1433);
        ctx.drawCenteredTextWithShadow(textRenderer,
                "DONATIL VISUALS", x + PANEL_W / 2, y + 14, 0xFF9D6BFF);
        ctx.drawCenteredTextWithShadow(textRenderer,
                "v2.4.1 · render-only", x + PANEL_W / 2, y + 32, 0xFF6F688F);

        // строки модулей
        int rowY = y + HEADER_H;
        for (Module m : modules()) {
            boolean hovered = mouseX >= x && mouseX < x + PANEL_W
                    && mouseY >= rowY && mouseY < rowY + ROW_H;
            if (hovered) {
                ctx.fill(x + 1, rowY + 1, x + PANEL_W - 1, rowY + ROW_H - 1, 0x22FFFFFF);
            }

            ctx.drawTextWithShadow(textRenderer, m.name(),
                    x + 14, rowY + 6, 0xFFFFFFFF);
            ctx.drawTextWithShadow(textRenderer, m.description(),
                    x + 14, rowY + 19, 0xFF6F688F);

            String state = m.enabled() ? "ON" : "OFF";
            int color = m.enabled() ? 0xFF5CFFC0 : 0xFF6F688F;
            ctx.drawTextWithShadow(textRenderer, state,
                    x + PANEL_W - 36, rowY + 12, color);

            rowY += ROW_H;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = (width - PANEL_W) / 2;
            int y = (height - panelHeight()) / 2;
            int rowY = y + HEADER_H;
            for (Module m : modules()) {
                if (mouseX >= x && mouseX < x + PANEL_W
                        && mouseY >= rowY && mouseY < rowY + ROW_H) {
                    m.toggle();
                    return true;
                }
                rowY += ROW_H;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) { // закрыть той же клавишей
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false; // не ставим одиночную игру на паузу
    }
}
