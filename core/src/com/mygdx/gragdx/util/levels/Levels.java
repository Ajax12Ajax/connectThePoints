package com.mygdx.gragdx.util.levels;

import com.badlogic.gdx.Gdx;
import com.mygdx.gragdx.screens.game.Fields;
import com.mygdx.gragdx.util.Constants;

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

    public void Level() {
        Levels prefs = Levels.instance;
        prefs.load();
        stage = prefs.stage;
        stage = 1;

        if (stage == 1) {
            prefs.load();
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
                    timer = 29;
                    rounds = 2;
                    levelName = "FiveByFive";
                    break;
                case 9:
                    prefs.level = 1;
                    prefs.save();
                    break;
            }
        }
        if (stage == 2) {
            switch (level) {
                case 1:
                case 2:
                case 4:
                case 5:
                case 6:
                    levelName = "FourByFour";
                    break;
                case 3:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 9:
                    levelName = "SixBySix";
                    break;
                case 10:
                    prefs.level = 1;
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
                case 10:
                    levelName = "SixBySix";
                    break;
            }
        }
        if (stage == 4) {
            switch (level) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    levelName = "FiveByFive";
                    break;
                case 1:
                case 2:
                case 3:
                    levelName = "SixBySix";
                    break;
            }
        }
        if (stage == 5) {
            switch (level) {
                case 7:
                case 8:
                case 9:
                    levelName = "FourByFour";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    levelName = "FiveByFive";
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
