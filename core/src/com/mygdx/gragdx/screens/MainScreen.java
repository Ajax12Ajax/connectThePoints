package com.mygdx.gragdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.gragdx.util.Constants;

import java.util.HashSet;
import java.util.Set;

public class MainScreen extends AbstractGameScreen {
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    private final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
    private final Stage menuStage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private final Set<Action> animationActions = new HashSet<Action>();

    private Skin skin;

    Label timerText;

    private MenuScreen menuScreen;

    float Y = 4;


    public MainScreen(Game game) {
        super(game);
    }


    private void setupUi() {
        menuScreen = new MenuScreen();

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_HUD_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_HUD_UI));

        setupBackground();

        //setupGame();

        //add Menu
        menuScreen.menuButtons(skin, menuStage, backgroundStage).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setupTimer();
                Gdx.input.setInputProcessor(stage);
            }
        });
    }


    private void setupBackground() {
        //Image image = new Image(skin, "backgroundStart");
        //backgroundUi.addActor(image);
    }

    //public void setupGame() {
    //    Table table = new Table();
    //    table.setFillParent(true);
    //    table.center();
//
    //    for (int pixelY = 0; pixelY < Y; pixelY++) {
    //        //Button gameButton = new Button(skin, "pauseButton");
    //        if (pixelY <= 4) {
    //            pixelY = 0;
    //            //gameButton.row();
    //            }
    //        //table.add(gameButton);
    //    }
//
    //    stage.addActor(table);
    //}

    public void setupTimer() {
        Table timerTable = new Table();
        timerTable.setFillParent(true);
        timerTable.top();

        timerText = new Label("2137", skin);
        timerText.setFontScale(1.5f);
        timerTable.add(timerText).padTop(20);

        registerAction(timerText, Actions.sequence(
                Actions.visible(false),
                Actions.alpha(0, 0.17f),
                Actions.visible(true),
                Actions.alpha(1, 0.17f, Interpolation.exp5Out)));

        stage.addActor(timerTable);
    }


    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);

        setupUi();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.706f, 0.851f, 0.847f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isButtonJustPressed(0)) {
            for (Action action : animationActions) {
                //noinspection StatementWithEmptyBody
                while (!action.act(100))
                    ; // finish all animations, needs multiple calls since a sequence only steps the current action
            }
        }

        drawStage(backgroundStage, deltaTime);
        drawStage(stage, deltaTime);
        drawStage(menuStage, deltaTime);
    }

    private void drawStage(Stage stage, Float deltaTime) {
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        backgroundStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        menuStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        Dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
        Dispose();
    }

    private void Dispose() {
        stage.dispose();
        backgroundStage.dispose();
        skin.dispose();
        menuStage.dispose();
    }
}