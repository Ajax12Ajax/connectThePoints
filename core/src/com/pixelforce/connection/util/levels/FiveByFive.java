package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;

public class FiveByFive {
    int[] f1 = new int[10];
    int[] f2 = new int[10];
    int[] f3 = new int[10];
    int[] f4 = new int[10];
    int[] f5 = new int[10];

    int[] Round1 = new int[10];
    int[] Round2 = new int[10];
    int[] Round3 = new int[10];
    int[] Round4 = new int[10];
    int[] Round5 = new int[10];

    int[] finalFields = new int[10];

    boolean restart = false;


    public void create() {
        System.arraycopy(generating(), 0, Round1, 0, 10);
        System.arraycopy(generating(), 0, Round2, 0, 10);
        System.arraycopy(generating(), 0, Round3, 0, 10);
        System.arraycopy(generating(), 0, Round4, 0, 10);
        System.arraycopy(generating(), 0, Round5, 0, 10);
    }

    public int[] pick(int round) {
        int[] Round = new int[10];
        switch (round) {
            case 0:
                Round = Round1;
                break;
            case 1:
                Round = Round2;
                break;
            case 2:
                Round = Round3;
                break;
            case 3:
                Round = Round4;
                break;
            case 4:
                Round = Round5;
                break;
        }
        return Round;
    }

    public int[] generating() {
        f1 = genr();

        f2 = genr(2, 3);

        f3 = genr(3, MathUtils.random(2, 3));
        restart();

        f4 = genr(4, 3);
        restart();

        f5 = genr(5, 3);
        restart();


        int emptyFields = 0;
        for (int i = 0; i < 25; i++) {
            //field[i].setVisible(true);
            int emptyField = 0;
            for (int j : f1) if (i != j) emptyField++;
            for (int j : f2) if (i != j) emptyField++;
            for (int j : f3) if (i != j) emptyField++;
            for (int j : f4) if (i != j) emptyField++;
            for (int j : f5) if (i != j) emptyField++;

            int all = f1.length + f2.length + f3.length + f4.length + f5.length;
            if (all == emptyField) {
                emptyFields++;
                around(i);
                if (Levels.emptyFields) {
                    //field[i].setVisible(false);
                }
            }
        }
        if (Levels.emptyFields) {
            if (emptyFields <= 2 || emptyFields >= 5)
                generating();
        } else {
            if (MathUtils.randomBoolean())
                if (emptyFields > 2)
                    generating();
            if (emptyFields >= 5)
                generating();
        }


        int gg = 0;
        for (int i = 0; i < 5; i++) {
            int[] fm = f1;

            switch (i) {
                case 1:
                    fm = f2;
                    break;
                case 2:
                    fm = f3;
                    break;
                case 3:
                    fm = f4;
                    break;
                case 4:
                    fm = f5;
                    break;
            }
            for (int t = 1; t <= 2; t++) {
                int ff = fm[0];
                if (t == 2) ff = fm[fm.length - 1];
                finalFields[gg] = ff;
                gg++;
            }
        }
        return finalFields;
    }


    private int[] genr(int f, int min) {
        int restarts = 0;
        int Field = MathUtils.random(0, 24);
        int steps = MathUtils.random(4, 7);
        int step = 0;
        int lastField = 0;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        int[] fm = new int[steps];
        random(f, Field);

        while (step < steps) {
            int side = MathUtils.random(1, 4);

            switch (side) {
                case 1:
                    if (Field - 5 >= 0) {
                        Field = Field - 5;
                    }
                    up = true;
                    break;
                case 2:
                    if (Field - 1 >= 0 && Field != 5 && Field != 10 && Field != 15 && Field != 20) {
                        Field = Field - 1;
                    }
                    left = true;
                    break;
                case 3:
                    if (Field + 5 <= 24) {
                        Field = Field + 5;
                    }
                    down = true;
                    break;
                case 4:
                    if (Field + 1 <= 24 && Field != 4 && Field != 9 && Field != 14 && Field != 19) {
                        Field = Field + 1;
                    }
                    right = true;
                    break;
            }


            if (up && down && left && right) {
                if (min >= step) {
                    restarts++;
                    Field = MathUtils.random(0, 24);
                    steps = MathUtils.random(4, 7);
                    step = 0;
                    lastField = 0;
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    fm = new int[steps];
                    random(f, Field);
                } else {
                    int[] fmf = new int[step];
                    for (int i = 0; i < fm.length; i++) {
                        if (step > i)
                            fmf[i] = fm[i];
                    }
                    fm = fmf;
                    step = steps;
                }
            } else if (lastField == Field || sameField(f, fm, Field, step)) {
                Field = lastField;

            } else {
                up = false;
                down = false;
                left = false;
                right = false;
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
                    if (Field - 5 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 5 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                }

                if (wrongField == 2) {
                    step = 0;
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    restarts++;
                }

                lastField = Field;

                fm[step] = Field;

                step++;
            }
            if (restarts == 60) {
                step = steps;
                restart = true;
            }
        }

        return fm;
    }

    private int[] genr() {
        int Field = MathUtils.random(0, 24);
        int steps = MathUtils.random(4, 6);
        int step = 0;
        int lastField = 0;
        int[] fm = new int[steps];

        while (step < steps) {
            int side = MathUtils.random(1, 4);
            switch (side) {
                case 1:
                    if (Field - 5 >= 0) {
                        Field = Field - 5;
                    }
                    break;
                case 2:
                    if (Field - 1 >= 0 && Field != 5 && Field != 10 && Field != 15 && Field != 20) {
                        Field = Field - 1;
                    }
                    break;
                case 3:
                    if (Field + 5 <= 24) {
                        Field = Field + 5;
                    }
                    break;
                case 4:
                    if (Field + 1 <= 24 && Field != 4 && Field != 9 && Field != 14 && Field != 19) {
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
                    if (Field - 5 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 5 == fm[i]) {
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


    private void restart() {
        if (restart) {
            restart = false;
            generating();
        }
    }

    private void random(int f, int Field) {
        if (f == 2) {
            for (int i = 0; i < f1.length; i++)
                if (Field == f1[i]) {
                    Field = MathUtils.random(0, 24);
                    i = 0;
                }
        }
        if (f == 3) {
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    if (Field == f2[k] || Field == f1[i]) {
                        Field = MathUtils.random(0, 24);
                        k = 0;
                        i = 0;
                    }
        }
        if (f == 4) {
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    for (int l = 0; l < f3.length; l++)
                        if (Field == f1[i] || Field == f2[k] || Field == f3[l]) {
                            Field = MathUtils.random(0, 24);
                            k = 0;
                            i = 0;
                            l = 0;
                        }
        }
        if (f == 5) {
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    for (int l = 0; l < f3.length; l++)
                        for (int u = 0; u < f4.length; u++)
                            if (Field == f1[i] || Field == f2[k] || Field == f3[l] || Field == f4[u]) {
                                Field = MathUtils.random(0, 24);
                                k = 0;
                                i = 0;
                                l = 0;
                                u = 0;
                            }
        }
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
        if (f == 5)
            for (int j : f4)
                if (Field == j) {
                    sameField = true;
                    break;
                }
        return sameField;
    }

    private void around(int field) {
        for (int u = 1; u < 6; u++) {
            int[] fm = new int[6];
            int a = 0;
            switch (u) {
                case 1:
                    fm = f1;
                    break;
                case 2:
                    fm = f2;
                    break;
                case 3:
                    fm = f3;
                    break;
                case 4:
                    fm = f4;
                    break;
                case 5:
                    fm = f5;
                    break;
            }

            for (int j : fm)
                for (int i = 3; i < 7; i++) {
                    if (i == 3) i = 1;
                    if (field + i == j)
                        a++;
                    if (field - i == j)
                        a++;
                    if (i == 1) i = 3;
                }
            if (a >= 5) {
                restart = true;
            }
        }
    }
}