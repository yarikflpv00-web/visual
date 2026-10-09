package gg.donatil.visuals.modules.visual;

import gg.donatil.visuals.modules.Module;

/**
 * Мягкий fullbright через опцию гаммы — без миксинов и без магии.
 * Меняется только то, как клиент ОТРИСОВЫВАЕТ свет.
 */
public class FullbrightModule extends Module {

    private double previousGamma = 1.0;

    public FullbrightModule() {
        super("Fullbright", "Высокая гамма освещения", false);
    }

    @Override
    protected void onEnable() {
        previousGamma = mc.options.getGamma().getValue();
        mc.options.getGamma().setValue(12.0); // выше максимума ползунка
    }

    @Override
    protected void onDisable() {
        mc.options.getGamma().setValue(previousGamma);
    }
}
