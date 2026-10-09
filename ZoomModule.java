package gg.donatil.visuals.modules.visual;

import gg.donatil.visuals.modules.Module;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * Зум на удержание C.
 *
 * Без нестабильных FOV-ивентов: мы временно меняем клиентскую опцию FOV,
 * а при отпускании клавиши возвращаем её обратно. Это чисто локальная
 * настройка изображения, серверу ничего не отправляется.
 */
public class ZoomModule extends Module {

    private static KeyBinding zoomKey;

    private boolean zooming = false;
    private int previousFov = 70;

    public ZoomModule() {
        super("Smooth Zoom", "Приближение на удержание C", true);

        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.donatil-visuals.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.donatil-visuals"
        ));
    }

    @Override
    public void tick() {
        if (mc.player == null) return;

        boolean pressed = zoomKey.isPressed();

        if (enabled() && pressed && !zooming) {
            previousFov = mc.options.getFov().getValue();
            mc.options.getFov().setValue(20); // сильное приближение
            zooming = true;
        } else if ((!enabled() || !pressed) && zooming) {
            mc.options.getFov().setValue(previousFov);
            zooming = false;
        }
    }

    @Override
    protected void onDisable() {
        if (zooming) {
            mc.options.getFov().setValue(previousFov);
            zooming = false;
        }
    }
}
