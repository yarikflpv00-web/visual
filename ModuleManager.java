package gg.donatil.visuals.modules;

import gg.donatil.visuals.modules.visual.FpsHudModule;
import gg.donatil.visuals.modules.visual.FullbrightModule;
import gg.donatil.visuals.modules.visual.KeystrokesModule;
import gg.donatil.visuals.modules.visual.ZoomModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public void registerDefaults() {
        modules.add(new FpsHudModule());
        modules.add(new KeystrokesModule());
        modules.add(new FullbrightModule());
        modules.add(new ZoomModule());
    }

    public List<Module> list() { return modules; }

    public void tick(MinecraftClient client) {
        for (Module m : modules) if (m.enabled()) m.tick();
    }

    public void renderHud(DrawContext ctx, float tickDelta) {
        for (Module m : modules) if (m.enabled()) m.renderHud(ctx, tickDelta);
    }
}
