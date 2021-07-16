package com.pixelforce.connection.util.levels;

import com.badlogic.gdx.math.MathUtils;

public class FourByFour {
    int[] f1 = new int[10];
    int[] f2 = new int[10];
    int[] f3 = new int[10];
    int[] f4 = new int[10];

    int[] Round1 = new int[10];
    int[] Round2 = new int[10];
    int[] Round3 = new int[10];
    int[] Round4 = new int[10];
    int[] Round5 = new int[10];

    int[] finalFields = new int[10];

    boolean restart = false;


    public void create() {
        System.arraycopy(generating(), 0, Round1, 0, 8);
        System.arraycopy(generating(), 0, Round2, 0, 8);
        System.arraycopy(generating(), 0, Round3, 0, 8);
        System.arraycopy(generating(), 0, Round4, 0, 8);
        System.arraycopy(generating(), 0, Round5, 0, 8);
    }

    public int[] pick(int round){
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

        f2 = genr(2);

        f3 = genr(3);
        restart();

        f4 = genr(4);
        restart();


        int emptyFields = 0;
        for (int i = 0; i < 16; i++) {
            int emptyField = 0;
            for (int j : f1) if (i != j) emptyField++;
            for (int j : f2) if (i != j) emptyField++;
            for (int j : f3) if (i != j) emptyField++;
            for (int j : f4) if (i != j) emptyField++;

            int all = f1.length + f2.length + f3.length + f4.length;
            if (all == emptyField) {
                emptyFields++;
                around(i);
            }
        }
        if (emptyFields >= 2 || restart) {
            if (restart) restart = false;
            generating();
        }


        int gg = 0;
        for (int i = 0; i < 4; i++) {
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


    private int[] genr(int f) {
        int restarts = 0;
        int Field = MathUtils.random(0, 15);
        int steps = 6;
        int step = 0;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        int[] fm = new int[steps];
        Field = random(f, Field);
        int lastField = Field;
        fm[step] = Field;
        step++;
        if (restart)
            step = steps;


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


            if (up && down && left && right) {
                if (step <= 2) {
                    restarts++;
                    restarts++;
                    Field = MathUtils.random(0, 15);
                    steps = 6;
                    step = 0;
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    fm = new int[steps];
                    Field = random(f, Field);
                    lastField = Field;
                    fm[step] = Field;
                    step++;
                    if (restart)
                        step = steps;
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
                    if (Field - 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                    if (Field + 4 == fm[i]) {
                        if (wrongField != 2)
                            wrongField++;
                    }
                }

                if (wrongField == 2) {
                    step--;
                    Field = lastField;
                    restarts++;
                }

                lastField = Field;

                fm[step] = Field;

                step++;
            }
            if (restarts == 80) {
                step = steps;
                restart = true;
            }
        }
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


    private void restart() {
        if (restart) {
            restart = false;
            generating();
        }
    }


    private int random(int f, int Field) {
        if (f == 2) {
            for (int i = 0; i < f1.length; i++)
                if (Field == f1[i]) {
                    Field = MathUtils.random(0, 15);
                    i = -1;
                }
        }
        if (f == 3) {
            Field = 0;
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    if (Field == f1[i] || Field == f2[k]) {
                        if (Field >= 15) {
                            restart = true;
                            i = f1.length;
                            k = f2.length;
                        } else {
                            k = 0;
                            i = 0;
                        }
                        Field++;
                    }
        }
        if (f == 4) {
            Field = 0;
            for (int i = 0; i < f1.length; i++)
                for (int k = 0; k < f2.length; k++)
                    for (int l = 0; l < f3.length; l++)
                        if (Field == f1[i] || Field == f2[k] || Field == f3[l]) {
                            if (Field >= 15) {
                                restart = true;
                                i = f1.length;
                                k = f2.length;
                                l = f3.length;
                            } else {
                                k = 0;
                                i = 0;
                                l = 0;
                            }
                            Field++;
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
        if (f >= 2)
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

    private void around(int field) {
        for (int u = 1; u < 5; u++) {
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
            }

            for (int j : fm)
                for (int i = 1; i < 6; i++)
                    if (i != 2) {
                        if (field + i == j)
                            a++;
                        if (field - i == j)
                            a++;
                    }
            if (a >= 5) {
                restart = true;
            }
        }
    }
}