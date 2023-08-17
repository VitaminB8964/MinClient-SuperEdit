package cn.floatingpoint.min.system.module.impl.boost.impl;

import cn.floatingpoint.min.system.module.impl.boost.BoostModule;
import cn.floatingpoint.min.system.module.impl.render.impl.KeyStrokes;
import cn.floatingpoint.min.system.module.value.impl.IntegerValue;
import cn.floatingpoint.min.system.module.value.impl.OptionValue;
import cn.floatingpoint.min.utils.client.Pair;
import net.minecraft.client.settings.KeyBinding;

/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class AutoClicker extends BoostModule {
    public final IntegerValue leftclick = new IntegerValue(0,20,1,14);
    public final IntegerValue rightclick = new IntegerValue(0,20,1,12);
    public final OptionValue left = new OptionValue(true);
    private final OptionValue right = new OptionValue(false);
    public static boolean autoclickerenable = false;
    @Override
    public void onEnable() {autoclickerenable = true;}
    @Override
    public void onDisable() {autoclickerenable = false;}
    @Override
    public void tick(){
        // Left click
        if (mc.gameSettings.keyBindAttack.isKeyDown() && isEnabled() && left.getValue() && KeyStrokes.leftCounter.size() <= leftclick.getValue()) {
                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode());
        }

        // Right click
        if (mc.gameSettings.keyBindUseItem.isKeyDown() && isEnabled() && right.getValue() && KeyStrokes.rightCounter.size() <= rightclick.getValue()){
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
        }

    }
    public AutoClicker(){
        addValues(
                new Pair<>("Leftspeed",leftclick),
                new Pair<>("Rightspeed",rightclick),
                new Pair<>("Left",left),
                new Pair<>("Right",right)
        );
    }
}
