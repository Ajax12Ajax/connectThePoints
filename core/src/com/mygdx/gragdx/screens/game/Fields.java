package com.mygdx.gragdx.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.gragdx.util.Constants;

public class Fields {
    private final Skin skin;

    public static int quantity = 16;
    public static int row = 4;

    Boolean[] startingField = new Boolean[quantity + 1];
    Boolean[] Check0 = new Boolean[quantity + 1];
    Boolean[] Check1 = new Boolean[quantity + 1];
    Boolean[] Check2 = new Boolean[quantity + 1];
    Boolean[] Check3 = new Boolean[quantity + 1];
    Boolean[] Check4 = new Boolean[quantity + 1];

    Boolean[] Connected1 = new Boolean[3];
    Boolean[] Connected2 = new Boolean[3];
    Boolean[] Connected3 = new Boolean[3];
    Boolean[] Connected4 = new Boolean[3];
    public int i1 = 1;
    public int i2 = 1;
    public int i3 = 1;
    public int i4 = 1;

    public Fields() {
        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_GAME_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_GAME_UI));
    }


    public void reset() {
        startingField = new Boolean[quantity + 1];
        Check0 = new Boolean[quantity + 1];
        Check1 = new Boolean[quantity + 1];
        Check2 = new Boolean[quantity + 1];
        Check3 = new Boolean[quantity + 1];
        Check4 = new Boolean[quantity + 1];

        for (int i = 0; i < quantity; i++) {
            startingField[i] = false;
            Check0[i] = false;
            Check1[i] = false;
            Check2[i] = false;
            Check3[i] = false;
            Check4[i] = false;
        }

        for (int i = 0; i < 3; i++) {
            Connected1[i] = false;
            Connected2[i] = false;
            Connected3[i] = false;
            Connected4[i] = false;
        }
    }

    public void setCheck(int i, Image image, Boolean check, String Check) {
        if (Check.equals("Check0")) {
            Check0[i] = check;
            image.setDrawable(skin, "field-dn");
        }
        if (Check.equals("Check1")) {
            Check1[i] = check;
            image.setDrawable(skin, "field-blue");
        }
        if (Check.equals("Check2")) {
            Check2[i] = check;
            image.setDrawable(skin, "field-red");
        }
        if (Check.equals("Check3")) {
            Check3[i] = check;
            image.setDrawable(skin, "field-purple");
        }
        if (Check.equals("Check4")) {
            Check4[i] = check;
            image.setDrawable(skin, "field-green");
        }
    }

    public Boolean getCheck(int i, String Check) {
        Boolean[] check = new Boolean[quantity + 1];
        check[i] = Check1[i];
        if (Check.equals("Check0")) {
            check[i] = Check0[i];
        }
        if (Check.equals("Check1")) {
            check[i] = Check1[i];
        }
        if (Check.equals("Check2")) {
            check[i] = Check2[i];
        }
        if (Check.equals("Check3")) {
            check[i] = Check3[i];
        }
        if (Check.equals("Check4")) {
            check[i] = Check4[i];
        }
        return check[i];
    }

    public String getColor(int i) {
        String check = "Check1";
        if (Check0[i]) {
            check = "Check0";
        }
        if (Check1[i]) {
            check = "Check1";
        }
        if (Check2[i]) {
            check = "Check2";
        }
        if (Check3[i]) {
            check = "Check3";
        }
        if (Check4[i]) {
            check = "Check4";
        }
        return check;
    }

    public void setStartingField(int i) {
        startingField[i] = true;
    }

    public Boolean getStartingField(int i) {
        return startingField[i];
    }

    public void setConnected(int i, String Check, Boolean check) {
        if (check) {
            if (getStartingField(i)) {
                if (Check.equals("Check1")) {
                    if (i1 == 3) {
                        i1 = 1;
                    }
                    Connected1[i1] = true;
                    i1++;
                }
                if (Check.equals("Check2")) {
                    if (i2 == 3) {
                        i2 = 1;
                    }
                    Connected2[i2] = true;
                    i2++;
                }
                if (Check.equals("Check3")) {
                    if (i3 == 3) {
                        i3 = 1;
                    }
                    Connected3[i3] = true;
                    i3++;
                }
                if (Check.equals("Check4")) {
                    if (i4 == 3) {
                        i4 = 1;
                    }
                    Connected4[i4] = true;
                    i4++;
                }
            }
        } else if (!check) {
            if (getStartingField(i)) {
                if (Check.equals("Check1")) {
                    if (i1 == 2) {
                        i1 = 1;
                    }
                    if (i1 == 3) {
                        i1 = 2;
                    }
                    Connected1[i1] = false;
                }
                if (Check.equals("Check2")) {
                    if (i2 == 2) {
                        i2 = 1;
                    }
                    if (i2 == 3) {
                        i2 = 2;
                    }
                    Connected2[i2] = false;
                }
                if (Check.equals("Check3")) {
                    if (i3 == 2) {
                        i3 = 1;
                    }
                    if (i3 == 3) {
                        i3 = 2;
                    }
                    Connected3[i3] = false;
                }
                if (Check.equals("Check4")) {
                    if (i4 == 2) {
                        i4 = 1;
                    }
                    if (i4 == 3) {
                        i4 = 2;
                    }
                    Connected4[i4] = false;
                }
            }
        }
    }

    public Boolean getConnected(String Check) {
        boolean Connected = false;
        if (Check.equals("Check1")) {
            Connected = Connected1[1] && Connected1[2];
        }
        if (Check.equals("Check2")) {
            Connected = Connected2[1] && Connected2[2];
        }
        if (Check.equals("Check3")) {
            Connected = Connected3[1] && Connected3[2];
        }
        if (Check.equals("Check4")) {
            Connected = Connected4[1] && Connected4[2];
        }
        return Connected;
    }
}