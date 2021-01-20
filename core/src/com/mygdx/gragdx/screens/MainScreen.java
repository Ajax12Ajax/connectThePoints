package com.mygdx.gragdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gragdx.screens.game.Game;
import com.mygdx.gragdx.util.levels.Levels;


public class MainScreen extends AbstractGameScreen {
    public Game game;
    private MenuScreen menuScreen;
    private Levels levels;


    public MainScreen(com.badlogic.gdx.Game game) {
        super(game);
    }


    @Override
    public void show() {
        game = new Game();
        menuScreen = new MenuScreen();
        levels = new Levels();

        game.setupGame();
        menuScreen.setupMenu();
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.406f, 0.010f, 0.999f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.draw(deltaTime);
        menuScreen.draw(deltaTime);

        if (game.endlevel) {
            menuScreen.start = false;
            menuScreen.setupLevelEnd();
            game.restartButton.setText("");
            game.timerText.setText("");
            game.roundsText.setText("");
            menuScreen.buttonNextLevel.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.stage.clear();
                    levels.Level();
                    game.setupGame();
                    menuScreen.start = true;
                    game.milliseconds = 0;
                    game.seconds = 0;
                }
            });
            menuScreen.buttonRepeat.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.stage.clear();
                    game.setupGame();
                    menuScreen.start = true;
                    game.milliseconds = 0;
                    game.seconds = 0;
                }
            });
            menuScreen.buttonHome.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.stage.clear();
                    levels.Level();
                    game.setupGame();
                    game.milliseconds = 0;
                    game.seconds = 0;
                }
            });
            game.endlevel = false;
        } else if (menuScreen.start) {
            game.update();
        }
    }


    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
        menuScreen.resize(width, height);
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
        game.dispose();
        menuScreen.dispose();
    }
}