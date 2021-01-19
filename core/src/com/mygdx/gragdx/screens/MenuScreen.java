package com.mygdx.gragdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.gragdx.util.Constants;
import com.mygdx.gragdx.util.levels.Levels;

import java.util.HashSet;
import java.util.Set;

public class MenuScreen {
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    private final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private final Set<Action> animationActions = new HashSet<Action>();

    private final OptionsMenu optionsMenu;

    Skin skin;

    Label buttonStart;
    Label buttonTools;
    Label buttonNextLevel;
    Label buttonRepeat;
    Label buttonHome;

    public Boolean start = false;
    float actionsDelay = 0.15f;


    public MenuScreen() {
        Gdx.input.setInputProcessor(stage);
        optionsMenu = new OptionsMenu();

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));
    }


    public void setupMenu() {
        final Image background = new Image(skin, "background");
        backgroundStage.addActor(background);


        final Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.center();

        buttonStart = new Label("Play", skin, "Roboto-Bold");
        menuTable.add(buttonStart).row();
        buttonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuTable.setVisible(false);
                background.setVisible(false);
                start = true;
            }
        });
        buttonAction(buttonStart);

        buttonTools = new Label("Options", skin, "Roboto-Thin");
        menuTable.add(buttonTools);
        buttonTools.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                optionsMenu.setupOptions(stage);
            }
        });
        actionsDelay = 0.35f;
        buttonAction(buttonTools);


        stage.addActor(menuTable);
    }

    public void setupLevelEnd() {
        Gdx.input.setInputProcessor(stage);

        final Image background = new Image(skin, "background");
        backgroundStage.addActor(background);

        final Table textTable = new Table();
        textTable.setFillParent(true);
        textTable.center();

        actionsDelay = 0.10f;
        Label text = new Label("Level", skin, "Roboto-Thin");
        textTable.add(text).padBottom(500);
        buttonAction(text);
        Label text2 = new Label("Completed", skin, "Roboto-Bold");
        textTable.add(text2).padBottom(500).padLeft(15).row();
        buttonAction(text2);

        stage.addActor(textTable);


        final Table table = new Table();
        table.setFillParent(true);
        table.center();

        actionsDelay = 0.25f;
        buttonNextLevel = new Label("Next Level", skin, "Roboto-Bold-Scaled-2");
        table.add(buttonNextLevel).row();
        buttonNextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Levels prefs = Levels.instance;
                prefs.level++;
                prefs.save();
                background.setVisible(false);
                textTable.setVisible(false);
                table.setVisible(false);
            }
        });
        buttonAction(buttonNextLevel);

        buttonRepeat = new Label("Replay", skin, "Roboto-Thin-Scaled-2");
        table.add(buttonRepeat).row();
        buttonRepeat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                background.setVisible(false);
                textTable.setVisible(false);
                table.setVisible(false);
            }
        });
        buttonAction(buttonRepeat);

        buttonHome = new Label("Home", skin, "Roboto-Thin-Scaled-2");
        table.add(buttonHome);
        buttonHome.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Levels prefs = Levels.instance;
                prefs.level++;
                prefs.save();
                background.setVisible(false);
                textTable.setVisible(false);
                table.setVisible(false);
                setupMenu();
            }
        });
        buttonAction(buttonHome);


        stage.addActor(table);
    }

    private void buttonAction(Label label) {
        registerAction(label, Actions.sequence(
                Actions.visible(false),
                Actions.alpha(0, 0.17f),
                Actions.delay(actionsDelay),
                Actions.visible(true),
                Actions.alpha(1, 0.17f, Interpolation.exp5Out)));
    }

    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }


    public void draw(float deltaTime) {
        backgroundStage.getViewport().apply();
        backgroundStage.act(deltaTime);
        backgroundStage.draw();
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
        optionsMenu.draw(deltaTime);
    }

    public void resize(int width, int height) {
        backgroundStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        optionsMenu.resize(width, height);
    }

    public void dispose() {
        backgroundStage.dispose();
        stage.dispose();
        skin.dispose();
        optionsMenu.dispose();
    }
}
