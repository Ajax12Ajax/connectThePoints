package com.mygdx.gragdx.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.gragdx.screens.game.Fields;

public class FourByFour {
    int name = MathUtils.random(1, 7);

    public void create(Fields fields, Image field[], Boolean restart) {
        if (restart)
            name = MathUtils.random(1, 7);
        for (int i = 0; i < 8; i++) {
            if (i <= 1) {
                fields.setStartingField(field(name, i));
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check1");
            }
            if (i >= 2 && i <= 3) {
                fields.setStartingField(field(name, i));
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check2");
            }
            if (i >= 4 && i <= 5) {
                fields.setStartingField(field(name, i));
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check3");
            }
            if (i >= 6) {
                fields.setStartingField(field(name, i));
                fields.setCheck(field(name, i), field[field(name, i)], true, "Check4");
            }
        }
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

        return field[number];
    }
}
