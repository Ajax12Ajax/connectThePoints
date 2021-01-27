package com.mygdx.gragdx.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.gragdx.screens.game.Fields;

public class SixBySix {
    int name = MathUtils.random(1, 1);

    public void create(Fields fields, Image[] field, Boolean restart) {
        if (restart)
            name = MathUtils.random(1, 1);

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
    }


    public int field(int name, int number) {
        int[] field = new int[8];

        if (name == 1) {
            field = new int[]{0, 30, 1, 31, 2, 32, 3, 33, 4, 34};
        }

        return field[number];
    }
}
