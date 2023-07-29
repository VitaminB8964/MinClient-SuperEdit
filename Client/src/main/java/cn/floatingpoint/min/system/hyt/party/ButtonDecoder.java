package cn.floatingpoint.min.system.hyt.party;

import cn.floatingpoint.min.system.ui.hyt.party.VexViewButton;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class ButtonDecoder {
    private final String[] elements;
    public final boolean invited;
    public final String inviter;
    public final boolean sign;

    public ButtonDecoder(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String result = decode(bytes);
        sign = result.contains("[gui]https://img.166.net/gameyw-misc/opd/squash/20221221/104939-4q3d0pgm59.png");
        if (sign) {
            result = result.replace("[but]", "[but]sign");
        }
        elements = result.split("<&>");
        int index = this.containsString("邀请你加入队伍");
        if (index != -1) {
            invited = true;
            System.out.println(this.elements[index - 3]);
            inviter = this.elements[index - 3].replace(":<#>[txt]50", "").replace("\2476玩家 \2473\247l", "");
        } else {
            invited = false;
            inviter = "";
        }
    }

    private String decode(byte[] bytes) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
            byte[] array = new byte[256];
            int read;
            while ((read = gZIPInputStream.read(array)) >= 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            return byteArrayOutputStream.toString("UTF-8");
        } catch (IOException ignored) {
        }
        return "";
    }

    public boolean containsButtons(String... buttons) {
        for (String button : buttons) {
            if (!this.containsButton(button)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsButton(String btn) {
        for (String element : elements) {
            if (element.endsWith("[but]" + btn)) {
                return true;
            }
        }
        return false;
    }

    public VexViewButton getButton(String name) {
        for (int i = 0; i < this.elements.length; ++i) {
            String e = this.elements[i];
            if (e.endsWith("[but]" + name)) {
                return new VexViewButton(name, this.elements[i + 6]);
            }
        }
        return null;
    }

    public int getButtonIndex(String name) {
        for (int i = 0; i < this.elements.length; ++i) {
            String e = this.elements[i];
            if (e.endsWith("[but]" + name)) {
                return i;
            }
        }
        return 0;
    }

    public int containsString(String s) {
        for (int i = 0; i < this.elements.length; ++i) {
            String e = this.elements[i];
            if (e.contains(s)) {
                return i;
            }
        }
        return -1;
    }

    public String getElement(int index) {
        return elements[index];
    }
}