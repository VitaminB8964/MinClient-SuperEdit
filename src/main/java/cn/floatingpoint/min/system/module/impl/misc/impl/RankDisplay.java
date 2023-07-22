package cn.floatingpoint.min.system.module.impl.misc.impl;

import cn.floatingpoint.min.management.Managers;
import cn.floatingpoint.min.system.module.impl.misc.MiscModule;
import cn.floatingpoint.min.system.module.value.impl.ModeValue;
import cn.floatingpoint.min.system.module.value.impl.OptionValue;
import cn.floatingpoint.min.utils.client.Pair;

/**
 * @projectName: MIN
 * @author: vlouboos
 * @date: 2023-07-22 15:07:57
 */
public class RankDisplay extends MiscModule {
    public static ModeValue game = new ModeValue(new String[]{"bw", "bw-xp", "sw", "kit"}, "bw") {
        @Override
        public void setValue(String value) {
            Managers.clientManager.ranks.clear();
            super.setValue(value);
        }
    };

    public RankDisplay() {
        addValues(new Pair<>("Game", game));
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void tick() {

    }
}
