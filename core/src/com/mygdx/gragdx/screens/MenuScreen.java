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
import com.mygdx.gragdx.screens.game.Game;
import com.mygdx.gragdx.util.Constants;

import java.util.HashSet;
import java.util.Set;

public class MenuScreen {
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    private final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private final Set<Action> animationActions = new HashSet<Action>();

    private OptionsMenu optionsMenu;
    private Game game;

    Skin skin;

    Label buttonStart;
    Label buttonTools;

    public Boolean start = false;
    float actionsDelay = 0.15f;


    public MenuScreen() {
        Gdx.input.setInputProcessor(stage);
        optionsMenu = new OptionsMenu();
        game = new Game();

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));
    }


    public void setupMenu() {
        final Image background= new Image(skin, "background");
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
                Gdx.input.setInputProcessor(game.stage);
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
