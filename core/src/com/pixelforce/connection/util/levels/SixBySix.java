package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pixelforce.connection.screens.game.Fields;

public class SixBySix {
    int name = 0;
    int namelast = 0;
    int namelast2 = 0;

    public void create(Fields fields, Image[] field, Boolean restart) {
        if (restart) {
            name = MathUtils.random(1, 6);
            if (namelast == name)
                name = MathUtils.random(1, 6);
            if (namelast2 == name)
                name = MathUtils.random(1, 6);
        }

        for (int i = 0; i < 10; i++) {
            fields.setStartingField(field(name, i));

            if (i <= 1) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check1");
            }
            if (i >= 2 && i <= 3) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check2");
            }
            if (i >= 4 && i <= 5) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check3");
            }
            if (i >= 6 && i <= 7) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check4");
            }
            if (i >= 8) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check5");
            }
        }
        namelast = name;
        namelast2 = namelast;
    }


    private int field(int name, int number) {
        int[] field = new int[10];

        if (name == 1) {
            field = new int[]{0, 30, 1, 31, 2, 32, 3, 33, 4, 34};
        }
        if (name == 2) {
            field = new int[]{7, 16, 12, 20, 3, 23, 18, 29, 0, 0};
        }
        if (name == 3) {
            field = new int[]{14, 35, 15, 29, 9, 23, 5, 6, 7, 30};
        }
        if (name == 4) {
            field = new int[]{17, 28, 21, 35, 16, 20, 5, 12, 18, 34};
        }
        if (name == 5) {
            field = new int[]{28, 32, 5, 21, 1, 10, 7, 31, 0, 30};
        }
        if (name == 6) {
            field = new int[]{27, 35, 14, 33, 5, 21, 2, 16, 1, 18};
        }

        return field[number];
    }
}
