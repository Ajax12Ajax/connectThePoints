package com.mygdx.gragdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.gragdx.screens.MainScreen;

public class MyGdxGame extends Game {
    private static final String TAG = MyGdxGame.class.getName();

    @Override
    public void create() {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Start game at menu screen
        setScreen(new MainScreen(this));
    }
}