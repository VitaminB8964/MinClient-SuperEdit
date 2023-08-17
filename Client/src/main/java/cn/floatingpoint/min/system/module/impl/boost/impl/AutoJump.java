package cn.floatingpoint.min.system.module.impl.boost.impl;

import cn.floatingpoint.min.system.module.impl.boost.BoostModule;
/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class AutoJump extends BoostModule {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void tick() {
        if (mc.player != null && mc.world != null)
            if (mc.player.onGround && mc.player.getMoveSpeed() > 0)
                mc.player.jump();
    }

}
