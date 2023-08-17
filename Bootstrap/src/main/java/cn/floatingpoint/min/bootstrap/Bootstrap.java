package cn.floatingpoint.min.bootstrap;

import cn.floatingpoint.min.bootstrap.exceptions.InitiateException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @projectName: MIN
 * @author: vlouboos
 * @date: 2023-07-29 13:49:01
 */
public class Bootstrap extends JFrame {
    private static JLabel label;
    private static JProgressBar progressBar;
    private static Bootstrap instance;
    private File dir;
    private boolean canLaunch = false;

    public Bootstrap(String[] args) {
        instance = this;
        System.setProperty("java.net.preferIPv4Stack", "true");
        OptionParser optionparser = new OptionParser();
        optionparser.allowsUnrecognizedOptions();
        optionparser.accepts("demo");
        optionparser.accepts("fullscreen");
        optionparser.accepts("checkGlErrors");
        optionparser.accepts("server").withRequiredArg();
        optionparser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(25565);
        OptionSpec<File> fileOptionSpec = optionparser.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."));
        optionparser.accepts("assetsDir").withRequiredArg().ofType(File.class);
        optionparser.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
        optionparser.accepts("proxyHost").withRequiredArg();
        optionparser.accepts("proxyPort").withRequiredArg().defaultsTo("8080", new String[0]).ofType(Integer.class);
        optionparser.accepts("proxyUser").withRequiredArg();
        optionparser.accepts("proxyPass").withRequiredArg();
        optionparser.accepts("username").withRequiredArg().defaultsTo("狂笑的蛇将写散文");
        optionparser.accepts("uuid").withRequiredArg();
        optionparser.accepts("accessToken").withRequiredArg().required();
        optionparser.accepts("version").withRequiredArg().required();
        optionparser.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(854);
        optionparser.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(480);
        optionparser.accepts("userProperties").withRequiredArg().defaultsTo("{}");
        optionparser.accepts("profileProperties").withRequiredArg().defaultsTo("{}");
        optionparser.accepts("assetIndex").withRequiredArg();
        optionparser.accepts("userType").withRequiredArg().defaultsTo("legacy");
        OptionSet optionset = optionparser.parse(args);
        File file = optionset.valueOf(fileOptionSpec);
        this.dir = (new File(file, "GameCore/MIN-Edit")).getAbsoluteFile();
        if (!this.dir.exists()) {
            while (!this.dir.mkdirs()) {
                this.dir = (new File(file, "GameCore/MIN-Edit")).getAbsoluteFile();
            }
        }

        this.setTitle("MIN Client Bootstrap(十月你妈妈没了)");
        this.setSize(360, 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        label = new JLabel("MIN Client is loading...");
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        this.add(label);
        this.add(progressBar);
        JLabel introduction = new JLabel("Developed by Potatochipscn.");
        introduction.setHorizontalAlignment(SwingConstants.CENTER);
        introduction.setVerticalAlignment(SwingConstants.BOTTOM);
        this.getContentPane().add(introduction);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String[] args) {
        if (instance != null) {
            throw new InitiateException("Method called twice!");
        }
        new Bootstrap(args).clientStart();
        instance.prepareGame(args);
    }

    private void clientStart() {
        new Thread(() -> {
            try {
                label.setText("Checking status: ");
                progressBar.setValue(33);
                progressBar.setValue(66);
                progressBar.setValue(99);
                progressBar.setValue(100);
                canLaunch = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error while grabbing data.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }).start();
    }



    @SuppressWarnings("all")
    private void prepareGame(String[] args) {
        while (!this.canLaunch) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        this.launchGame(args);
    }

    private void launchGame(String[] args) {
        label.setText("                  Launching client...                  ");
        progressBar.setVisible(false);
        File jar = new File(this.dir, "Game.jar");
        try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jar.toURI().toURL()})) {
            Class<?> launcherClass = urlClassLoader.loadClass("cn.floatingpoint.min.launcher.Launcher");
            Method launchMethod = launcherClass.getMethod("launch", String[].class);
            this.setVisible(false);
            launchMethod.invoke(launcherClass, (Object) args);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            JOptionPane.showMessageDialog(this, "Error while launching game.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        System.exit(0);
    }
}
