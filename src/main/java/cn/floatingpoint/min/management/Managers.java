package cn.floatingpoint.min.management;

import cn.floatingpoint.min.management.impl.*;

import java.util.HashSet;
import java.util.Set;

public class Managers {
    public static Set<Manager> managers = new HashSet<>();
    public static CheaterManager cheaterManager = new CheaterManager();
    public static ClientMateManager clientMateManager = new ClientMateManager();
    public static DraggableGameViewManager draggableGameViewManager = new DraggableGameViewManager();
    public static FileManager fileManager = new FileManager();
    public static FontManager fontManager = new FontManager();
    public static HYTPacketManager hytPacketManager = new HYTPacketManager();
    public static I18NManager i18NManager = new I18NManager();
    public static ModuleManager moduleManager = new ModuleManager();

    static {
        managers.add(cheaterManager);
        managers.add(clientMateManager);
        managers.add(fileManager);
        managers.add(fontManager);
        managers.add(hytPacketManager);
        managers.add(i18NManager);
        managers.add(moduleManager);
        managers.add(draggableGameViewManager);
    }

    public static void init() {
        System.out.println("Start initialize managements.");
        long startMS = System.currentTimeMillis();
        long currentMS = System.currentTimeMillis();
        for (Manager manager : managers) {
            System.out.println("Initiating " + manager.getName());
            manager.init();
            System.out.println("Initiated " + manager.getName() + " in " + (System.currentTimeMillis() - currentMS) + "ms.");
            currentMS = System.currentTimeMillis();
        }
        System.out.println("Initialized all managements in " + (currentMS - startMS) + "ms.");
    }
}