package com.mygdx.gragdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.mygdx.gragdx.screens.game.Game;


public class MainScreen extends AbstractGameScreen {
    public Game game;
    private MenuScreen menuScreen;


    public MainScreen(com.badlogic.gdx.Game game) {
        super(game);
    }


    @Override
    public void show() {
        game = new Game();
        menuScreen = new MenuScreen();

        game.setupGame();
        menuScreen.setupMenu();
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.406f, 0.010f, 0.999f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.draw(deltaTime);
        menuScreen.draw(deltaTime);

        if (menuScreen.start) {
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