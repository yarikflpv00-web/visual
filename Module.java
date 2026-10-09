package gg.donatil.visuals.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/**
 * Базовый модуль. Жёсткое правило проекта: модуль может только
 * читать состояние игры и рисовать на экране. Ничего не отправляем.
 */
public abstract class Module {

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String description;
    private boolean enabled;

    protected Module(String name, String description, boolean enabledByDefault) {
        this.name = name;
        this.description = description;
        this.enabled = enabledByDefault;
    }

    public String name() { return name; }
    public String description() { return description; }
    public boolean enabled() { return enabled; }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable(); else onDisable();
    }

    protected void onEnable() {}
    protected void onDisable() {}
    public void tick() {}
    public void renderHud(DrawContext ctx, float tickDelta) {}
}
