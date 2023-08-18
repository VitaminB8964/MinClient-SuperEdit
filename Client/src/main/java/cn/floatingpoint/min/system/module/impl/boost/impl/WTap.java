package cn.floatingpoint.min.system.module.impl.boost.impl;

import cn.floatingpoint.min.system.module.impl.boost.BoostModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class WTap extends BoostModule {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void tick() {
        if (mc.player.attackedOther){

            if (mc.player.isSprinting()){
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),false);
            }
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),true);
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),false);
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),true);
        }
    }
}
