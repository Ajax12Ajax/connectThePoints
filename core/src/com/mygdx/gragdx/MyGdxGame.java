package com.mygdx.gragdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.gragdx.screens.MainScreen;
import com.mygdx.gragdx.util.Preferences;

public class MyGdxGame extends Game {
    private static final String TAG = MyGdxGame.class.getName();

    @Override
    public void create() {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Load preferences for audio settings and start playing music
        Preferences.instance.load();
        //AudioManager.instance.play(Assets.instance.music.song01);
        // Start game at menu screen
        setScreen(new MainScreen(this));
    }
}