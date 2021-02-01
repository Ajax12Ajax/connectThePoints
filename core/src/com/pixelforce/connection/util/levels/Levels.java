package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.Gdx;
import com.pixelforce.connection.screens.game.Fields;
import com.pixelforce.connection.util.Constants;

public class Levels {
    public static final Levels instance = new Levels();
    public int level;
    public int stage;
    private final com.badlogic.gdx.Preferences prefs;


    public Levels() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }

    public void load() {
        level = prefs.getInteger("level", 1);
        stage = prefs.getInteger("stage", 1);
    }

    public void save() {
        prefs.putInteger("level", level);
        prefs.putInteger("stage", stage);
        prefs.flush();
    }


    public static String levelName = "FourByFour";
    public int timer = 1;
    public int rounds = 1;
    public static int levelStats;
    public static int levelStats2;

    public void Level() {
        Levels prefs = Levels.instance;
        prefs.load();

        if (prefs.stage == 1) {
            levelStats = 8;
            Constants.TEXTURE_ATLAS_GAME_UI = "images/gameTexture1.atlas";
            switch (prefs.level) {
                case 1:
                    timer = 27;
                    rounds = 3;
                    levelName = "FourByFour";
                    break;
                case 2:
                    timer = 21;
                    rounds = 3;
                    levelName = "FourByFour";
                    break;
                case 3:
                    timer = 20;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 4:
                    timer = 18;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 5:
                case 6:
                    timer = 24;
                    rounds = 5;
                    levelName = "FourByFour";
                    break;
                case 7:
                    timer = 17;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 8:
                    timer = 22;
                    rounds = 1;
                    levelName = "FiveByFive";
                    levelStats2 = 9;
                    break;
                case 9:
                    prefs.stage = 2;
                    prefs.level = 1;
                    prefs.save();
                    break;
            }
        }
        if (prefs.stage == 2) {
            levelStats = 9;
            Constants.TEXTURE_ATLAS_GAME_UI = "images/gameTexture2.atlas";
            switch (prefs.level) {
                case 1:
                    timer = 17;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 2:
                    timer = 16;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 3:
                    timer = 17;
                    rounds = 1;
                    levelName = "FiveByFive";
                    break;
                case 4:
                case 6:
                    timer = 15;
                    rounds = 4;
                    levelName = "FourByFour";
                    break;
                case 5:
                    timer = 12;
                    rounds = 3;
                    levelName = "FourByFour";
                    break;
                case 7:
                    timer = 21;
                    rounds = 2;
                    levelName = "FiveByFive";
                    break;
                case 8:
                    timer = 20;
                    rounds = 2;
                    levelName = "FiveByFive";
                    break;
                case 9:
                    timer = 15;
                    rounds = 1;
                    levelName = "FiveByFive";
                    levelStats = 10;
                    break;
                case 10:
                    prefs.level = 1;
                    prefs.stage = 3;
                    prefs.save();
                    break;
            }
        }
        if (stage == 3) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 9:
                    levelName = "SixBySix";
                    break;
                case 10:
                    prefs.level = 1;
                    prefs.stage = 4;
                    prefs.save();
                    break;
            }
        }
        if (stage == 4) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 9:
                    levelName = "SixBySix";
                    break;
                case 10:
                    prefs.level = 1;
                    prefs.stage = 5;
                    prefs.save();
                    break;
            }
        }
        if (stage == 5) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 9:
                    levelName = "SixBySix";
                    break;
                case 10:
                    prefs.level = 9;
                    prefs.save();
                    break;
            }
        }
        if (stage == 6) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 9:
                    levelName = "SixBySix";
                    break;
                case 10:
                    prefs.level = 9;
                    prefs.save();
                    break;
            }
        }

        if (levelName.equals("FourByFour")) {
            Fields.quantity = 16;
            Fields.row = 4;
        }
        if (levelName.equals("FiveByFive")) {
            Fields.quantity = 25;
            Fields.row = 5;
        }
        if (levelName.equals("SixBySix")) {
            Fields.quantity = 36;
            Fields.row = 6;
        }
    }
}
