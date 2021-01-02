package com.mygdx.gragdx.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.gragdx.screens.MainScreen;
import com.mygdx.gragdx.util.Constants;

public class Level1 {
    private static final String TAG = MainScreen.class.getName();
    private Skin skin;
    private Field field;

    public Image[] image = new Image[17];

    int last;

    Boolean test = false;
    int[] start1 = new int[3];
    int start2 = 1;


    public void addLevel(Table table) {
        field = new Field();

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_HUD_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_HUD_UI));


        int t = 3;
        for (int i = 0; i < 16; i++) {
            image[i] = new Image(skin, "field-dn");
            field.setCheck(i, image[i], false, "Check1");
            image[i].setDrawable(skin, "field-dn");
            table.add(image[i]).padRight(15).padBottom(15);
            if (i == t) {
                t = t + 4;
                table.row();
            }
        }

        field.setStartingField(2);
        field.setCheck(2, image[2], true, "Check1");
        field.setStartingField(3);
        field.setCheck(3, image[3], true, "Check3");
        field.setStartingField(4);
        field.setCheck(4, image[4], true, "Check1");
        field.setStartingField(8);
        field.setCheck(8, image[8], true, "Check4");
        field.setStartingField(9);
        field.setCheck(9, image[9], true, "Check3");
        field.setStartingField(10);
        field.setCheck(10, image[10], true, "Check2");
        field.setStartingField(14);
        field.setCheck(14, image[14], true, "Check4");
        field.setStartingField(15);
        field.setCheck(15, image[15], true, "Check2");
    }

    public void level() {


        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 16; i++) {
                if (field.getStartingField(i)) {
                    Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                    Vector2 mouseLocalPosition = image[i].screenToLocalCoordinates(mouseScreenPosition);
                    if (image[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                        start1[start2] = i;
                        if (start2 == 2) {
                            start2 = 1;
                        }
                        start2++;
                        test = true;
                    }
                }
            }
        } else {
            test = false;
            for (int i = 0; i < 2; i++) {
                start1[i] = 0;
            }
            start2 = 1;
            last = 0;
        }


        if (test) {
            for (int i = 0; i < 16; i++) {
                Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Vector2 mouseLocalPosition = image[i].screenToLocalCoordinates(mouseScreenPosition);
                if (image[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                    if (last != i) {
                        if (!field.getCheck(i, field.getColor(last))) {
                            field.setCheck(i, image[i], true, field.getColor(last));

                            if (field.getStartingField(i)) {
                                field.setCheck(i, image[i], true, field.getColor(i));
                                field.setConnected(i, field.getColor(i));
                            }

                        } else if (field.getCheck(i, field.getColor(last))) {
                            field.setCheck(last, image[last], false, field.getColor(last));
                            image[last].setDrawable(skin, "field-dn");
                            if (start1[2] == i) {
                                field.setCheck(last, image[last], true, field.getColor(i));
                            }
                            if (start1[1] == i) {
                                field.setCheck(last, image[last], false, field.getColor(last));
                                image[last].setDrawable(skin, "field-dn");
                            }
                            if (field.getStartingField(last)) {
                                field.setCheck(last, image[last], true, field.getColor(i));
                            }
                            if (field.getStartingField(i)) {
                                field.setConnected(i, field.getColor(i));
                            }
                        }
                        last = i;

                    }
                }
                if (field.getConnected(field.getColor(0))) {
                   field.setCheck(0, image[0], true, field.getColor(0));
                }
            }
        }


        if (!test) {
            for (int i = 0; i < 16; i++) {
                if (!field.getStartingField(i)) {
                    if (!field.getConnected(field.getColor(i))) {
                        field.setCheck(i, image[i], false, "Check1");
                        field.setCheck(i, image[i], false, "Check2");
                        field.setCheck(i, image[i], false, "Check3");
                        field.setCheck(i, image[i], false, "Check4");
                        image[i].setDrawable(skin, "field-dn");
                        field.i1 = 1;
                        field.i2 = 1;
                        field.i3 = 1;
                        field.i4 = 1;
                    }
                }
            }
        }
    }
}
