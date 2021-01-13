package com.mygdx.gragdx.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.gragdx.util.Constants;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private static final String TAG = Game.class.getName();
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    public final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private final Set<Action> animationActions = new HashSet<Action>();

    private final Skin skinGame;
    private final Skin skinMenu;

    private final Fields fields;

    Label timerText;
    Image[] field = new Image[17];
    Label restartButton;

    Boolean touched = false;
    int[] start1 = new int[3];
    int start2 = 1;
    int lastField;
    int timer = 0;


    public Game() {
        fields = new Fields();

        skinGame = new Skin(
                Gdx.files.internal(Constants.SKIN_GAME_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_GAME_UI));
        skinMenu = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));
    }


    public void setupGame() {
        Image background = new Image(skinGame, "background");
        backgroundStage.addActor(background);


        Table timerTable = new Table();
        timerTable.setFillParent(true);
        timerTable.top();

        timerText = new Label("", skinMenu, "Roboto-Thin");
        timerTable.add(timerText).padTop(20);
        stage.addActor(timerTable);


        Table gameTable = new Table();
        gameTable.setFillParent(true);
        gameTable.center();

        int t = 3;
        for (int i = 0; i < 16; i++) {
            field[i] = new Image(skinGame, "field-dn");
            fields.setCheck(i, field[i], true, "Check0");
            if (i == t) {
                t = t + 4;
                gameTable.add(field[i]).padBottom(15);
                gameTable.row();
            } else {
                gameTable.add(field[i]).padRight(15).padBottom(15);
            }
        }
        stage.addActor(gameTable);


        Table restartTable = new Table();
        restartTable.setFillParent(true);
        restartTable.bottom();

        restartButton = new Label("", skinMenu, "Roboto-Thin-Scaled");
        restartTable.add(restartButton).padBottom(20);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fields.reset();
                stage.clear();
                setupGame();
            }
        });
        stage.addActor(restartTable);
        stage.setDebugAll(false);


        fields.setStartingField(2);
        fields.setCheck(2, field[2], true, "Check1");
        fields.setStartingField(3);
        fields.setCheck(3, field[3], true, "Check3");
        fields.setStartingField(4);
        fields.setCheck(4, field[4], true, "Check1");
        fields.setStartingField(8);
        fields.setCheck(8, field[8], true, "Check4");
        fields.setStartingField(9);
        fields.setCheck(9, field[9], true, "Check3");
        fields.setStartingField(10);
        fields.setCheck(10, field[10], true, "Check2");
        fields.setStartingField(14);
        fields.setCheck(14, field[14], true, "Check4");
        fields.setStartingField(15);
        fields.setCheck(15, field[15], true, "Check2");
    }

    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }


    public void update() {
        Gdx.input.setInputProcessor(stage);
        restartButton.setText("RESTART");
        timerText.setText(String.valueOf(timer));
        timer++;

        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 16; i++) {
                if (fields.getStartingField(i)) {
                    Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                    Vector2 mouseLocalPosition = field[i].screenToLocalCoordinates(mouseScreenPosition);
                    if (field[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                        start1[start2] = i;
                        if (start2 == 2) {
                            start2 = 1;
                        }
                        start2++;
                        touched = true;
                    }
                }
            }
        } else {
            touched = false;
            for (int i = 0; i < 3; i++) {
                start1[i] = 0;
            }
            start2 = 1;
            lastField = 0;
        }


        if (touched) {
            for (int i = 0; i < 16; i++) {
                Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Vector2 mouseLocalPosition = field[i].screenToLocalCoordinates(mouseScreenPosition);
                if (field[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                    if (lastField != i) {

                        if (fields.getColor(i).equals("Check0") && fields.getConnected(fields.getColor(lastField))) {

                        } else if (fields.getColor(i).equals("Check0")) {
                            if (lastField == i - 1 || lastField == i + 1 || lastField == i - 4 || lastField == i + 4) {
                                fields.setCheck(i, field[i], false, "Check0");
                                fields.setCheck(i, field[i], true, fields.getColor(lastField));
                            }

                        } else if (fields.getConnected(fields.getColor(i))) {
                            start2 = 1;
                            lastField = 0;
                            reset();

                        } else if (fields.getStartingField(i) && start1[1] != start1[2] && fields.getColor(i).equals(fields.getColor(lastField))) {
                            if (lastField == i - 1 || lastField == i + 1 || lastField == i - 4 || lastField == i + 4) {
                                fields.setConnected(i, fields.getColor(i), true);
                            } else {
                                start2 = 1;
                                lastField = 0;
                                reset();
                                fields.setConnected(i, fields.getColor(i), true);
                            }

                        } else if (fields.getStartingField(i) && (!fields.getConnected(fields.getColor(lastField)) || fields.getConnected(fields.getColor(lastField)) || fields.getConnected(fields.getColor(0)))) {
                            start2 = 1;
                            lastField = 0;
                            reset();
                            fields.setConnected(i, fields.getColor(i), true);

                        } else if (fields.getCheck(i, fields.getColor(i)) && !fields.getStartingField(lastField)) {
                            fields.setCheck(lastField, field[lastField], false, fields.getColor(lastField));
                            fields.setCheck(lastField, field[lastField], true, "Check0");

                            if (fields.getConnected(fields.getColor(0)) && fields.getCheck(0, fields.getColor(0))) {
                                fields.setCheck(0, field[0], false, "Check0");
                                fields.setCheck(0, field[0], true, fields.getColor(0));
                            }
                        }

                        lastField = i;
                    }
                }
            }
        }

        if (!touched) {
            reset();
        }
    }

    private void reset() {
        for (int i = 0; i < 16; i++) {
            if (!fields.getStartingField(i)) {
                if (!fields.getConnected(fields.getColor(i))) {
                    fields.setCheck(i, field[i], false, "Check1");
                    fields.setCheck(i, field[i], false, "Check2");
                    fields.setCheck(i, field[i], false, "Check3");
                    fields.setCheck(i, field[i], false, "Check4");
                    fields.setCheck(i, field[i], true, "Check0");
                    fields.i1 = 1;
                    fields.i2 = 1;
                    fields.i3 = 1;
                    fields.i4 = 1;
                }
            }
        }
    }


    public void draw(float deltaTime) {
        backgroundStage.getViewport().apply();
        backgroundStage.act(deltaTime);
        backgroundStage.draw();
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }

    public void resize(int width, int height) {
        backgroundStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        backgroundStage.dispose();
        stage.dispose();
        skinGame.dispose();
        skinMenu.dispose();
    }
}
