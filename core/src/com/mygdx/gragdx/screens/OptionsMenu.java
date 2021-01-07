package com.mygdx.gragdx.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.gragdx.util.AudioManager;
import com.mygdx.gragdx.util.Constants;
import com.mygdx.gragdx.util.Preferences;

import java.util.HashSet;
import java.util.Set;

public class OptionsMenu {
    private final Set<Action> animationActions = new HashSet<Action>();
    final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private Skin skin;

    private Table tableTools;

    private Button backgroundButton;
    private Slider slider;
    private CheckBox checkBox;


    public void setupOptions(Stage stageT) {
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));


        // + Background Button
        addBackgroundButton(stageT);

        // + Tools
        addToolsTable();

        Preferences prefs = Preferences.instance;
        slider.setValue(prefs.volMusic);
        checkBox.setChecked(prefs.music);

        //loadSettings();
        //Preferences prefs = Preferences.instance;
        //slider.setValue(prefs.volMusic);
        //saveSettings();
        //loadSettings();
        //AudioManager.instance.onSettingsUpdated();
    }


    private Table addBackgroundButton(final Stage stageT) {
        final Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.left();

        // + Background Button
        backgroundButton = new Button(skin, "background");
        backgroundButton.setScaleX(55);
        backgroundButton.setColor(1, 1, 1, 0);
        backgroundButton.setVisible(true);

        backgroundButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
                loadSettings();
                AudioManager.instance.onSettingsUpdated();
                Gdx.input.setInputProcessor(stageT);
                backgroundButton.setVisible(false);
                tableTools.setVisible(false);
            }
        });
        table.add(backgroundButton).padBottom(100);

        // Add table
        stage.addActor(table);

        return table;
    }



    private Table addToolsTable() {
        tableTools = new Table();
        tableTools.setFillParent(true);
        tableTools.setVisible(true);
        tableTools.bottom();

        checkBox = new CheckBox("", skin, "mute");
        int height = 57;
        int width = 50;
        checkBox.getStyle().checkboxOn.setMinHeight(height);
        checkBox.getStyle().checkboxOff.setMinHeight(height);
        checkBox.getStyle().checkboxOn.setMinWidth(width);
        checkBox.getStyle().checkboxOff.setMinWidth(width);
        tableTools.add(checkBox).padLeft(-100).padBottom(10);
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
                loadSettings();
                AudioManager.instance.onSettingsUpdated();
            }
        });


        slider = new Slider(0.0f, 1.0f, 0.01f, false, skin);

        final Container<Slider> container=new Container<Slider>(slider);
        container.setTransform(true);   // for enabling scaling and rotation
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
        container.size(210, 20);
        container.setScale(1.25f);

        tableTools.add(container).padBottom(7);
        container.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!checkBox.isChecked()) {
                    Preferences prefs = Preferences.instance;
                    slider.setValue(prefs.volMusic);
                }
                saveSettings();
                loadSettings();
                AudioManager.instance.onSettingsUpdated();
            }
        });


        registerAction(tableTools, Actions.sequence(
                Actions.visible(false),
                Actions.moveBy(0, -1000, 0.15f),
                Actions.visible(true),
                Actions.moveBy(0, 1000, 0.15f, Interpolation.exp5Out)));

        stage.addActor(tableTools);

        return tableTools;
    }

    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }


    public void loadSettings() {
        Preferences prefs = Preferences.instance;
        prefs.load();
        checkBox.setChecked(prefs.music);
        slider.setValue(prefs.volMusic);
    }

    private void saveSettings() {
        Preferences prefs = Preferences.instance;
        prefs.music = checkBox.isChecked();
        prefs.volMusic = slider.getValue();
        prefs.save();
    }


    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }

    public void draw(float deltaTime) {
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }
}
