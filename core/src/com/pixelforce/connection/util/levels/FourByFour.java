package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pixelforce.connection.screens.game.Fields;

public class FourByFour {
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

        for (int i = 0; i < 8; i++) {
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
            if (i >= 6) {
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check4");
            }
        }
        namelast = name;
        namelast2 = namelast;
    }


    public int field(int name, int number) {
        int[] field = new int[8];

        if (name == 1) {
            field = new int[]{2, 4, 10, 15, 3, 9, 8, 14};
        }
        if (name == 2) {
            field = new int[]{0, 6, 3, 9, 15, 12, 8, 5};
        }
        if (name == 3) {
            field = new int[]{5, 12, 8, 1, 2, 15, 3, 11};
        }
        if (name == 4) {
            field = new int[]{6, 8, 0, 3, 7, 15, 12, 14};
        }
        if (name == 5) {
            field = new int[]{10, 15, 11, 0, 6, 8, 12, 9};
        }
        if (name == 6) {
            field = new int[]{8, 5, 4, 2, 6, 12, 3, 15};
        }
        if (name == 7) {
            field = new int[]{14, 7, 3, 0, 6, 9, 5, 13};
        }
        if (name == 8) {
            field = new int[]{0, 10, 12, 15, 2, 11, 1, 6};
        }
        if (name == 9) {
            field = new int[]{0, 6, 1, 11, 12, 15, 8, 10};
        }
        if (name == 10) {
            field = new int[]{3, 4, 6, 15, 5, 8, 10, 12};
        }
        if (name == 11) {
            field = new int[]{3, 4, 5, 7, 8, 14, 9, 15};
        }
        if (name == 12) {
            field = new int[]{3, 15, 4, 6, 5, 8, 10, 12};
        }
        if (name == 13) {
            field = new int[]{8, 15, 0, 9, 1, 10, 3, 11};
        }
        if (name == 14) {
            field = new int[]{6, 15, 8, 14, 4, 10, 0, 3};
        }
        if (name == 15) {
            field = new int[]{6, 8, 3, 4, 7, 10, 12, 15};
        }
        if (name == 16) {
            field = new int[]{6, 14, 2, 13, 3, 15, 0, 12};
        }
        if (name == 17) {
            field = new int[]{7, 9, 3, 8, 10, 15, 12, 14};
        }
        if (name == 18) {
            field = new int[]{5, 11, 1, 8, 12, 15, 2, 7};
        }
        if (name == 19) {
            field = new int[]{9, 15, 5, 12, 0, 6, 3, 10};
        }
        if (name == 20) {
            field = new int[]{7, 9, 1, 3, 11, 13, 0, 12};
        }
        if (name == 21) {
            field = new int[]{1, 7, 0, 9, 5, 11, 12, 15};
        }
        if (name == 22) {
            field = new int[]{0, 6, 3, 10, 9, 15, 5, 12};
        }
        if (name == 23) {
            field = new int[]{1, 10, 0, 9, 8, 14, 3, 15};
        }
        if (name == 24) {
            field = new int[]{7, 14, 3, 5, 1, 12, 10, 13};
        }
        if (name == 25) {
            field = new int[]{2, 4, 6, 8, 10, 12, 3, 15};
        }
        if (name == 26) {
            field = new int[]{8, 15, 7, 9, 3, 5, 1, 4};
        }
        if (name == 27) {
            field = new int[]{5, 15, 4, 13, 9, 14, 0, 3};
        }
        if (name == 28) {
            field = new int[]{10, 12, 7, 13, 0, 6, 1, 3};
        }
        if (name == 29) {
            field = new int[]{6, 14, 1, 15, 0, 9, 8, 13};
        }
        if (name == 30) {
            field = new int[]{7, 12, 11, 13, 0, 5, 1, 3};
        }
        if (name == 31) {
            field = new int[]{6, 15, 2, 11, 0, 9, 4, 13};
        }
        if (name == 32) {
            field = new int[]{10, 12, 7, 9, 0, 8, 1, 3};
        }
        if (name == 33) {
            field = new int[]{2, 8, 5, 12, 6, 15, 3, 11};
        }
        if (name == 34) {
            field = new int[]{1, 7, 0, 8, 9, 15, 12, 14};
        }
        if (name == 35) {
            field = new int[]{8, 15, 7, 9, 0, 6, 1, 3};
        }
        if (name == 36) {
            field = new int[]{7, 12, 11, 13, 1, 3, 0, 5};
        }
        if (name == 37) {
            field = new int[]{0, 9, 4, 10, 3, 6, 7, 15};
        }
        if (name == 38) {
            field = new int[]{7, 14, 6, 13, 5, 12, 0, 3};
        }
        if (name == 39) {
            field = new int[]{7, 14, 1, 3, 0, 6, 10, 12};
        }
        if (name == 40) {
            field = new int[]{13, 15, 11, 12, 4, 7, 0, 3};
        }

        return field[number];
    }
}
