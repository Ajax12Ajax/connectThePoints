package com.pixelforce.connection;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.pixelforce.connection.screens.MainScreen;
import com.pixelforce.connection.util.AudioManager;
import com.pixelforce.connection.util.Preferences;

public class MyGdxGame extends Game {

    @Override
    public void create() {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Load preferences for audio settings and start playing music
        Preferences.instance.load();
        AudioManager.instance.play(Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3")));
        // Start game at menu screen
        setScreen(new MainScreen(this));
    }
}