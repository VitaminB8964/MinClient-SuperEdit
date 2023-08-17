package cn.floatingpoint.min.system.module.impl.boost.impl;

import cn.floatingpoint.min.system.module.impl.boost.BoostModule;
import cn.floatingpoint.min.system.module.value.impl.DecimalValue;
import cn.floatingpoint.min.utils.client.ChatUtil;
import cn.floatingpoint.min.utils.client.Pair;
/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class Reach extends BoostModule {
    private final DecimalValue reach = new DecimalValue(0.0,6.0,0.1,3.2);
    private static boolean reachenable = false;
    @Override
    public void onEnable() {
        reachenable = true;
    }

    @Override
    public void onDisable() {
        reachenable = false;
    }

    @Override
    public void tick() {
        ChatUtil.printToChatWithPrefix("不要再开挂了草泥马");

    }
    public Reach(){
        addValues(
                new Pair<>("Reach",reach)
        );
    }
}
