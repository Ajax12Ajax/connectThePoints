package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pixelforce.connection.screens.game.Fields;

public class FiveByFive {
    int name = 0;
    int namelast = 0;
    int namelast2 = 0;

    public void create(Fields fields, Image[] field, Boolean restart) {
        if (restart) {
            name = MathUtils.random(1, 40);
            if (namelast == name)
                name = MathUtils.random(1, 40);
            if (namelast2 == name)
                name = MathUtils.random(1, 40);
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


    public int field(int name, int number) {
        int[] field = new int[10];

        if (name == 1) {
            field = new int[]{0, 21, 16, 3, 7, 24, 4, 19, 8, 18};
        }
        if (name == 2) {
            field = new int[]{0, 11, 7, 15, 20, 18, 22, 24, 19, 4};
        }

        return field[number];
    }
}
