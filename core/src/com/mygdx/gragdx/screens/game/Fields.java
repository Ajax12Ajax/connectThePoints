package com.mygdx.gragdx.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.gragdx.util.Constants;

public class Fields {
    private Skin skin;

    Boolean[] startingField = new Boolean[17];
    Boolean[] Check1 = new Boolean[17];
    Boolean[] Check2 = new Boolean[17];
    Boolean[] Check3 = new Boolean[17];
    Boolean[] Check4 = new Boolean[17];

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

        for (int i = 0; i < 16; i++) {
            startingField[i] = false;
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
        if (Check == "Check1") {
            Check1[i] = check;
            image.setDrawable(skin, "field-blue");
        } else if (Check == "Check2") {
            Check2[i] = check;
            image.setDrawable(skin, "field-red");
        } else if (Check == "Check3") {
            Check3[i] = check;
            image.setDrawable(skin, "field-purple");
        } else if (Check == "Check4") {
            Check4[i] = check;
            image.setDrawable(skin, "field-green");
        }
    }

    public Boolean getCheck(int i, String Check) {
        Boolean[] check = new Boolean[17];
        check[i] = Check1[i];
        if (Check == "Check1") {
            check[i] = Check1[i];
        }
        if (Check == "Check2") {
            check[i] = Check2[i];
        }
        if (Check == "Check3") {
            check[i] = Check3[i];
        }
        if (Check == "Check4") {
            check[i] = Check4[i];
        }
        return check[i];
    }

    public String getColor(int i) {
        String check = "Check1";
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
                if (Check == "Check1") {
                    if (i1 == 3) {
                        i1 = 1;
                    }
                    Connected1[i1] = true;
                    i1++;
                }
                if (Check == "Check2") {
                    if (i2 == 3) {
                        i2 = 1;
                    }
                    Connected2[i2] = true;
                    i2++;
                }
                if (Check == "Check3") {
                    if (i3 == 3) {
                        i3 = 1;
                    }
                    Connected3[i3] = true;
                    i3++;
                }
                if (Check == "Check4") {
                    if (i4 == 3) {
                        i4 = 1;
                    }
                    Connected4[i4] = true;
                    i4++;
                }
            }
        } else if (!check) {
            if (getStartingField(i)) {
                if (Check == "Check1") {
                    if (i1 == 2) {
                        i1 = 1;
                    }
                    if (i1 == 3) {
                        i1 = 2;
                    }
                    Connected1[i1] = false;
                }
                if (Check == "Check2") {
                    if (i2 == 2) {
                        i2 = 1;
                    }
                    if (i2 == 3) {
                        i2 = 2;
                    }
                    Connected2[i2] = false;
                }
                if (Check == "Check3") {
                    if (i3 == 2) {
                        i3 = 1;
                    }
                    if (i3 == 3) {
                        i3 = 2;
                    }
                    Connected3[i3] = false;
                }
                if (Check == "Check4") {
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
        Boolean Connected = false;
        if (Check == "Check1") {
            if (Connected1[1] && Connected1[2]) {
                Connected = true;
            } else {
                Connected = false;
            }
        }
        if (Check == "Check2") {
            if (Connected2[1] && Connected2[2]) {
                Connected = true;
            } else {
                Connected = false;
            }
        }
        if (Check == "Check3") {
            if (Connected3[1] && Connected3[2]) {
                Connected = true;
            } else {
                Connected = false;
            }
        }
        if (Check == "Check4") {
            if (Connected4[1] && Connected4[2]) {
                Connected = true;
            } else {
                Connected = false;
            }
        }
        return Connected;
    }
}