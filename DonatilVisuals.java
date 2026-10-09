package gg.donatil.visuals;

import gg.donatil.visuals.modules.ModuleManager;
import gg.donatil.visuals.ui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * Donatil Visuals — точка входа.
 *
 * Принцип: мод существует ТОЛЬКО на твоём экране. Он не отправляет
 * серверу ни одного пакета и не меняет механику игры — поэтому
 * за него не банят.
 */
public class DonatilVisuals implements ClientModInitializer {

    public static final String MOD_ID = "donatil-visuals";
    public static final ModuleManager MODULES = new ModuleManager();

    private static KeyBinding openGui;

    @Override
    public void onInitializeClient() {
        MODULES.registerDefaults();

        // ── МЕНЮ НА ПРАВЫЙ ШИФТ ─────────────────────────────
        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.donatil-visuals.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,      // <-- сама клавиша
                "category.donatil-visuals"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MODULES.tick(client);
            while (openGui.wasPressed()) {
                if (client.player != null
                        && !(client.currentScreen instanceof ClickGuiScreen)) {
                    client.setScreen(new ClickGuiScreen());
                }
            }
        });

        // отрисовка HUD-модулей поверх игры
        HudRenderCallback.EVENT.register((context, tickCounter) ->
                MODULES.renderHud(context, tickCounter.getTickDelta(true)));
    }
}
