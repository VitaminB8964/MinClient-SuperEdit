package cn.floatingpoint.min.system.module.impl.misc.impl;

import cn.floatingpoint.min.MIN;
import cn.floatingpoint.min.system.module.impl.misc.MiscModule;
import cn.floatingpoint.min.system.module.value.impl.OptionValue;
import cn.floatingpoint.min.system.module.value.impl.TextValue;
import cn.floatingpoint.min.utils.client.Pair;
import org.lwjgl.opengl.Display;

import java.util.Objects;

import static net.minecraft.client.Minecraft.DEBUG_MODE;
/**
 * @ProjectName MIN
 * @Author PotatochipsCN
 * @Date 2023/08
 */
public class TitleDisplay extends MiscModule {
    private final TextValue title = new TextValue("MinClient 1.12");
    private final OptionValue timedisplay = new OptionValue(false);
    private static int s = 0;
    private static int m = 0;
    private static int h = 0;
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        NormalTitle();
    }
    public TitleDisplay(){
        addValues(
                new Pair<>("Title",title),
                new Pair<>("TimeDisplay",timedisplay)
        );

    }

    @Override
    public void tick() {

        if (mc.player.ticksExisted == 20)
            s++;
        if (s==60)
            m++;
        if (m==60)
            h++;
        if (!Objects.equals(Display.getTitle(), title.getValue()) && !timedisplay.getValue()) {
            Display.setTitle(title.getValue());
        }
        if (timedisplay.getValue()) {
            Display.setTitle(title.getValue() + " | 已经殴打花雨庭:"+h+" 时"+m+" 分"+s+" 秒");
        }


    }
    private void NormalTitle(){
        if (DEBUG_MODE) {
            Display.setTitle("MIN Client(Minecraft 1.12.2) - DEBUG MODE");
        } else {
            Display.setTitle("MIN Client(Minecraft 1.12.2) - Release " + MIN.VERSION);
        }
    }
}
