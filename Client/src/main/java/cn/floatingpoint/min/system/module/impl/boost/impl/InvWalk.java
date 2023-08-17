package cn.floatingpoint.min.system.module.impl.boost.impl;

import cn.floatingpoint.min.management.Managers;
import cn.floatingpoint.min.system.module.impl.boost.BoostModule;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class InvWalk extends BoostModule {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void tick() {
        GameSettings gameSettings = mc.gameSettings;
        KeyBinding[] keyBindings = {
                gameSettings.keyBindForward,
                gameSettings.keyBindLeft,
                gameSettings.keyBindRight,
                gameSettings.keyBindSprint,
                gameSettings.keyBindBack,
                gameSettings.keyBindJump};
        for (KeyBinding keyBinding : keyBindings) {
            if (!mc.gameSettings.keyBindCommand.isKeyDown()){
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
                if (Managers.moduleManager.boostModules.get("Sprint").isEnabled())
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
            }
        }
    }

}
