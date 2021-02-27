package com.pixelforce.connection.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.pixelforce.connection.screens.game.Game;
import com.pixelforce.connection.util.levels.Levels;


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
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        game.setupGame(true);
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
            if (MenuScreen.completedLevel) {
                menuScreen.buttonNextLevel.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Levels prefs = Levels.instance;
                        prefs.level++;
                        prefs.save();
                        game.stage.clear();
                        levels.Level();
                        game.setupGame(true);
                        menuScreen.start = true;
                        game.milliseconds = 0;
                    }
                });
            }
            menuScreen.buttonReplay.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.stage.clear();
                    game.setupGame(true);
                    menuScreen.start = true;
                    game.milliseconds = 0;
                }
            });
            menuScreen.buttonHome.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (MenuScreen.completedLevel) {
                        Levels prefs = Levels.instance;
                        prefs.level++;
                        prefs.save();
                    }
                    game.stage.clear();
                    levels.Level();
                    game.setupGame(true);
                    game.milliseconds = 0;
                }
            });
            game.endlevel = false;
        } else if (menuScreen.start) {
            game.update();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.backButtonClicks++;
            if (game.backButtonClicks == 2) {
                game.backButtonClicks = 0;
                Gdx.app.exit();
            }
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.backButtonClicks = 0;
                }
            }, 3);
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
        Gdx.input.setCatchKey(Input.Keys.BACK, false);
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