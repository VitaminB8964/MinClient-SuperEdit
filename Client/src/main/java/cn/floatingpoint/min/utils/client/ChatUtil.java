package cn.floatingpoint.min.utils.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @projectName: MIN
 * @author: vlouboos
 * @date: 2023-07-20 11:16:33
 */
public class ChatUtil {
    public static void printToChatWithPrefix(String message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
    }

    public static void printToChat(ITextComponent textComponent) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(textComponent);
    }
    private static final Logger logger = LogManager.getLogger("Min");
    public static Logger getlogger(){
        return logger;
    }

}
