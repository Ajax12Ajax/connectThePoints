package com.pixelforce.connection.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.pixelforce.connection.MyGdxGame;

public class DesktopLauncher {
    private static boolean rebuildAtlas = false;
    private static boolean drawDebugOutline = false;

    public static void main(String[] arg) {
        if (rebuildAtlas) {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.duplicatePadding = false;
            settings.debug = drawDebugOutline;
            TexturePacker.process(settings, "desktop/assets-raw/images", "android/assets/images", "test");
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "connection";
        config.width = 500;
        config.height = 785;
        new LwjglApplication(new MyGdxGame(), config);
    }
}