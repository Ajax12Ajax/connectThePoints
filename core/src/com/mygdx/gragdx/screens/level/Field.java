package com.mygdx.gragdx.screens.level;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.gragdx.util.Constants;

public class Field {
    private Skin skin;

    Boolean[] startingField = new Boolean[17];
    Boolean[] Check1 = new Boolean[17];
    Boolean[] Check2 = new Boolean[17];
    Boolean[] Check3 = new Boolean[17];
    Boolean[] Check4 = new Boolean[17];

    public Field() {
        skin = new Skin(new TextureAtlas(Constants.TEXTURE_ATLAS_HUD_UI));

        for (int i = 0; i < 16; i++) {
            startingField[i] = false;
            Check1[i] = false;
            Check2[i] = false;
            Check3[i] = false;
            Check4[i] = false;
        }
    }

    public void setCheck(int i, Image image, Boolean check, String Check) {
        if (Check == "Check1") {
            Check1[i] = check;
            image.setDrawable(skin, "field-up-blue");
        }else if (Check == "Check2") {
            Check2[i] = check;
            image.setDrawable(skin, "field-up-red");
        }else if (Check == "Check3") {
            Check3[i] = check;
            image.setDrawable(skin, "field-up-purple");
        }else if (Check == "Check4") {
            Check4[i] = check;
            image.setDrawable(skin, "field-up-green");
        }
    }

    public Boolean getCheck(int i, String Check) {
        Boolean[] check = new Boolean[17];
        check[i] = Check1[i];
        if (Check == "Check1") {
            check[i] =  Check1[i];
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
        if (Check3[i]){
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
}