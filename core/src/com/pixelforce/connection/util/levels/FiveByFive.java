package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pixelforce.connection.screens.game.Fields;

public class FiveByFive {
    int name = 0;
    int namelast = 0;
    int namelast2 = 0;

    int[] emptyField = new int[10];

    public void create(Fields fields, Image[] field, Boolean restart) {
        if (!Levels.emptyFields) {
            if (restart) {
                name = MathUtils.random(1, 27);
                if (namelast == name)
                    name = MathUtils.random(1, 27);
                if (namelast2 == name)
                    name = MathUtils.random(1, 27);
            }
        } else {
            if (restart) {
                name = MathUtils.random(1, 2);
                if (namelast == name)
                    name = MathUtils.random(1, 2);
                if (namelast2 == name)
                    name = MathUtils.random(1, 2);

                name = -name;
            }
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

        if (Levels.emptyFields) {
            final Levels prefs = Levels.instance;
            prefs.load();

            if (emptyField[0] != 99 && prefs.stage >= 4) {
                for (int i = 0; i < 10; i++) {
                    if (emptyField[i] != 99) {
                        field[emptyField[i]].setVisible(false);
                    } else return;
                }
            }
        }
    }


    private int field(int name, int number) {
        emptyField = new int[]{99};
        int[] field = new int[10];

        if (name == -1) {
            field = new int[]{8, 24, 15, 23, 1, 10, 6, 17, 2, 9};
            emptyField = new int[]{20, 7, 18, 99};
        }
        if (name == -2) {
            field = new int[]{1, 10, 11, 20, 12, 24, 3, 14, 2, 8};
            emptyField = new int[]{0, 19, 13, 22, 21, 99};
        }


        if (name == 1) {
            field = new int[]{0, 21, 16, 3, 7, 24, 4, 19, 8, 18};
        }
        if (name == 2) {
            field = new int[]{0, 11, 7, 15, 20, 18, 22, 24, 19, 4};
        }
        if (name == 3) {
            field = new int[]{20, 23, 8, 24, 13, 15, 4, 5, 7, 10};
        }
        if (name == 4) {
            field = new int[]{8, 24, 15, 23, 1, 10, 6, 17, 2, 9};
        }
        if (name == 5) {
            field = new int[]{8, 17, 11, 18, 20, 23, 9, 24, 0, 4};
        }
        if (name == 6) {
            field = new int[]{12, 24, 16, 23, 11, 22, 4, 5, 0, 3};
        }
        if (name == 7) {
            field = new int[]{12, 23, 8, 22, 4, 24, 0, 3, 5, 20};
        }
        if (name == 8) {
            field = new int[]{8, 16, 0, 12, 6, 20, 19, 21, 3, 14};
        }
        if (name == 9) {
            field = new int[]{9, 24, 7, 18, 5, 17, 0, 4, 10, 23};
        }
        if (name == 10) {
            field = new int[]{13, 24, 7, 19, 12, 20, 0, 16, 4, 11};
        }
        if (name == 11) {
            field = new int[]{8, 23, 2, 17, 3, 24, 6, 22, 1, 20};
        }
        if (name == 12) {
            field = new int[]{12, 21, 9, 22, 6, 17, 0, 4, 5, 20};
        }
        if (name == 13) {
            field = new int[]{7, 16, 9, 12, 0, 8, 5, 22, 17, 24};
        }
        if (name == 14) {
            field = new int[]{18, 20, 17, 24, 8, 16, 2, 9, 1, 15};
        }
        if (name == 15) {
            field = new int[]{21, 24, 17, 20, 4, 18, 0, 3, 5, 13};
        }
        if (name == 16) {
            field = new int[]{9, 17, 16, 24, 13, 20, 0, 8, 1, 4};
        }
        if (name == 17) {
            field = new int[]{9, 24, 4, 13, 7, 23, 2, 5, 6, 15};
        }
        if (name == 18) {
            field = new int[]{6, 13, 7, 16, 4, 8, 9, 17, 20, 24};
        }
        if (name == 19) {
            field = new int[]{7, 24, 15, 23, 0, 16, 3, 6, 4, 13};
        }
        if (name == 20) {
            field = new int[]{10, 18, 12, 23, 15, 22, 4, 5, 0, 3};
        }
        if (name == 21) {
            field = new int[]{7, 23, 12, 20, 0, 16, 3, 11, 4, 24};
        }
        if (name == 22) {
            field = new int[]{7, 18, 8, 24, 1, 9, 0, 16, 10, 23};
        }
        if (name == 23) {
            field = new int[]{7, 16, 17, 20, 6, 15, 0, 9, 8, 24};
        }
        if (name == 24) {
            field = new int[]{16, 24, 17, 20, 9, 18, 5, 13, 0, 4};
        }
        if (name == 25) {
            field = new int[]{7, 18, 11, 22, 9, 0, 6, 21, 14, 23};
        }
        if (name == 26) {
            field = new int[]{8, 10, 3, 5, 4, 12, 19, 20, 21, 24};
        }
        if (name == 27) {
            field = new int[]{1, 10, 11, 20, 12, 24, 3, 14, 2, 8};
        }

        return field[number];
    }
}
