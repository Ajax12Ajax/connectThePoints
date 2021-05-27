package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pixelforce.connection.screens.game.Fields;

public class FourByFour {
    int[] f1 = new int[10];
    int[] f2 = new int[10];
    int[] f3 = new int[10];
    int[] f4 = new int[10];

    boolean restart = false;
    boolean restart2 = false;

    public void create(Fields fields, Image[] field, boolean reset) {
        Gdx.app.debug("", "1");
        if (reset) {
            f1 = genr();

            Gdx.app.debug("", "2");
            f2 = genr(2);

            Gdx.app.debug("", "3");
            f3 = genr(3);
            Gdx.app.debug("", "3a");
            restart(fields, field);
            Gdx.app.debug("", "3b");

            Gdx.app.debug("", "4");
            f4 = genr(4);
            Gdx.app.debug("", "4a");
            restart(fields, field);
            Gdx.app.debug("", "4b");
        }


        Gdx.app.debug("", "5");
        int emptyFields = 0;
        for (int i = 0; i < 16; i++) {
            field[i].setVisible(true);
            int emptyField = 0;
            for (int j : f1) if (i != j) emptyField++;
            for (int j : f2) if (i != j) emptyField++;
            for (int j : f3) if (i != j) emptyField++;
            for (int j : f4) if (i != j) emptyField++;

            int all = f1.length + f2.length + f3.length + f4.length;
            if (all == emptyField) {
                emptyFields++;
            }
        }
        Gdx.app.debug("", "6");
        if (reset) {
            if (emptyFields >= 1) {
                create(fields, field, true);
                Gdx.app.debug("ssssssssssssssssssssssssssssssssssssssssssssssss", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssss");




                // nwm jak ale zmnimalizować puste pola





            }
        }
        Gdx.app.debug("", "7");

        fields.reset();
        for (int i = 0; i < 16; i++)
            fields.setCheck(i, field[i], true, "Check0");
        Gdx.app.debug("", "8");

        for (int i = 0; i < 4; i++) {
            String color = "Check1";
            int[] fm = f1;

            switch (i) {
                case 1:
                    color = "Check2";
                    fm = f2;
                    break;
                case 2:
                    color = "Check3";
                    fm = f3;
                    break;
                case 3:
                    color = "Check4";
                    fm = f4;
                    break;
            }
            for (int t = 0; t < 2; t++) {
                int ff = fm[0];
                if (t == 1) ff = fm[fm.length - 1];
                fields.setStartingField(ff);
                fields.setCheck(ff, field[ff], true, color);
            }
        }
        Gdx.app.debug("", "9");
    }


    // dodać te sprawdzanie by dowiedzieć się w którym momencie zaczyna sie pętlować
    //                                                          w randomie też dodać by bardziej go przeszukać


    private int[] genr(int f) {


        // zmienić system wybierania pola startowego na od najblisze by zmniejszyć liczbe powtórzeń spowodowanych brakiem wypełnienia pola
        //                                            wogule jakoś zrobić by nie było pustych pól


        int restarts = 0;
        int Field = MathUtils.random(0, 15);
        int steps = 6;
        int step = 0;
        int lastField = 0;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        int[] fm = new int[steps];
        Gdx.app.debug("F" + f, "1");
        Field = random(f, Field);
        if (restart2) {
            Gdx.app.debug("F" + f, "1a");
            step = steps;
            Gdx.app.debug("F" + f, "1b");
            restart = true;
            Gdx.app.debug("F" + f, "1c");
        }
        Gdx.app.debug("F" + f, "2");

        while (step < steps) {
            int side = MathUtils.random(1, 4);

            switch (side) {
                case 1:
                    if (Field - 4 >= 0) {
                        Field = Field - 4;
                    }
                    up = true;
                    break;
                case 2:
                    if (Field - 1 >= 0 && Field != 4 && Field != 8 && Field != 12) {
                        Field = Field - 1;
                    }
                    left = true;
                    break;
                case 3:
                    if (Field + 4 <= 15) {
                        Field = Field + 4;
                    }
                    down = true;
                    break;
                case 4:
                    if (Field + 1 <= 15 && Field != 3 && Field != 7 && Field != 11) {
                        Field = Field + 1;
                    }
                    right = true;
                    break;
            }
            Gdx.app.debug("F" + f, "3");


            if (up && down && left && right) {
                if (2 >= step) {
                    restarts++;
                    restarts++;
                    Field = MathUtils.random(0, 15);
                    steps = 6;
                    step = 0;
                    lastField = 0;
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    fm = new int[steps];
                    Gdx.app.debug("F" + f, "4");
                    Gdx.app.debug("ttttttttttttttttttttttttttttttttttttttttttttttttttt", "   " + Field);
                    Field = random(f, Field);
                    if (restart2) {
                        Gdx.app.debug("F" + f, "1");
                        step = steps;
                        restart = true;
                    }
                    Gdx.app.debug("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy", "   " + Field);
                    Gdx.app.debug("F" + f, "5");
                } else {
                    Gdx.app.debug("F" + f, "6");
                    int[] fmf = new int[step];
                    for (int i = 0; i < fm.length; i++) {
                        if (step > i)
                            fmf[i] = fm[i];
                    }
                    fm = fmf;
                    step = steps;
                    Gdx.app.debug("F" + f, "7");
                }
            } else if (lastField == Field || sameField(f, fm, Field, step)) {
                Field = lastField;
                Gdx.app.debug("F" + f, "8");

            } else {
                up = false;
                down = false;
                left = false;
                right = false;
                int wrongField = 0;
                Gdx.app.debug("F" + f, "9");

                for (int i = 0; i < step; i++) {
                    if (Field - 1 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 1 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field - 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                }
                Gdx.app.debug("F" + f, "10");

                if (wrongField == 2) {
                    step--;
                    Field = lastField;
                    /// tu może zmienić coś
                    restarts++;
                }
                Gdx.app.debug("F" + f, "11");

                lastField = Field;

                fm[step] = Field;

                step++;
                Gdx.app.debug("F" + f, "12");
            }
            Gdx.app.debug("F" + f, "13");
            if (restarts == 40) {
                Gdx.app.debug("gggggggggggggggggggggggggggggggggggggggggggggggg", "gggggggggggggggggggggggggggggggggggggggggggggggggggggggg     " + f);
                step = steps;
                Gdx.app.debug("F" + f, "14");
                restart = true;
                Gdx.app.debug("F" + f, "15");
            }
            Gdx.app.debug("F" + f, "16");
        }
        Gdx.app.debug("F" + f, "17");
        return fm;
    }

    private int[] genr() {
        int Field = MathUtils.random(0, 15);
        int steps = MathUtils.random(3, 6);
        int step = 0;
        int lastField = 0;
        int[] fm = new int[steps];

        while (step < steps) {
            int side = MathUtils.random(1, 4);
            switch (side) {
                case 1:
                    if (Field - 4 >= 0) {
                        Field = Field - 4;
                    }
                    break;
                case 2:
                    if (Field - 1 >= 0 && Field != 4 && Field != 8 && Field != 12) {
                        Field = Field - 1;
                    }
                    break;
                case 3:
                    if (Field + 4 <= 15) {
                        Field = Field + 4;
                    }
                    break;
                case 4:
                    if (Field + 1 <= 15 && Field != 3 && Field != 7 && Field != 11) {
                        Field = Field + 1;
                    }
                    break;
            }

            boolean sameField = false;
            for (int i = 0; i < step; i++) {
                if (fm[i] == Field) {
                    sameField = true;
                    break;
                }
            }

            if (lastField == Field || sameField) {
                Field = lastField;
            } else {
                int wrongField = 0;

                for (int i = 0; i < step; i++) {
                    if (Field - 1 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 1 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field - 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                }
                if (wrongField == 2)
                    step = 0;

                lastField = Field;
                fm[step] = Field;
                step++;
            }
        }
        return fm;
    }


    private void restart(Fields fields, Image[] field) {
        if (restart) {
            restart = false;
            create(fields, field, true);
        }
    }

    private int random(int f, int Field) {
        Gdx.app.debug("R", "1");
        int restarts = 0;
        restart2 = false;
        Gdx.app.debug("R", "2");
        if (f == 2) {
            for (int i = 0; i < f1.length; i++)
                if (Field == f1[i]) {
                    Field = MathUtils.random(0, 15);
                    i = 0;
                }
        }
        Gdx.app.debug("R", "3");
        if (f == 3) {
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    if (Field == f2[k] || Field == f1[i]) {
                        Field = MathUtils.random(0, 15);
                        k = 0;
                        i = 0;
                    }
        }
        Gdx.app.debug("R", "4");
        if (f == 4) {
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    for (int l = 0; l < f3.length; l++)
                        if (Field == f1[i] || Field == f2[k] || Field == f3[l]) {
                            Field = MathUtils.random(0, 15);
                            restarts++;
                            if (restarts == 60) {
                                restart2 = true;
                                i = f1.length;
                                k = f2.length;
                                l = f3.length;
                            } else {
                                k = 0;
                                i = 0;
                                l = 0;
                            }
                        }
        }
        return Field;
    }

    private boolean sameField(int f, int[] fm, int Field, int step) {
        boolean sameField = false;
        for (int i = 0; i < step; i++)
            if (fm[i] == Field) {
                sameField = true;
                break;
            }
        for (int j : f1)
            if (Field == j) {
                sameField = true;
                break;
            }
        if (f >= 3)
            for (int j : f2)
                if (Field == j) {
                    sameField = true;
                    break;
                }
        if (f >= 4)
            for (int j : f3)
                if (Field == j) {
                    sameField = true;
                    break;
                }
        return sameField;
    }
}