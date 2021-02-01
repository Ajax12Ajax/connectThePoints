package com.pixelforce.connection.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Preferences {
    public static final Preferences instance = new Preferences();
    public boolean music;
    public float volMusic;
    private com.badlogic.gdx.Preferences prefs;

    // singleton: prevent instantiation from other classes
    private Preferences() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }

    public void load() {
        music = prefs.getBoolean("music", true);
        volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
    }

    public void save() {
        prefs.putBoolean("music", music);
        prefs.putFloat("volMusic", volMusic);
        prefs.flush();
    }
}