package com.mygdx.gragdx.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    private Button backgroundButton;
    private Table tableTools;
    private Slider slider;
    private Image speaker;


    public void setupOptions(Stage stageT) {
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));


        addBackgroundButton(stageT);

        addToolsTable();


        Preferences prefs = Preferences.instance;
        slider.setValue(prefs.volMusic);

        if (slider.getValue() > 0.5f) {
            speaker.setDrawable(skin, "speaker-high");
        } else if (slider.getValue() == 0.0f) {
            speaker.setDrawable(skin, "speaker-low");
        } else {
            speaker.setDrawable(skin, "speaker-medium");
        }
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
        stage.addActor(table);

        return table;
    }


    private Table addToolsTable() {
        tableTools = new Table();
        tableTools.setFillParent(true);
        tableTools.setVisible(true);
        tableTools.bottom();

        speaker = new Image(skin, "speaker-high");
        speaker.setScale(0.73f);
        tableTools.add(speaker).padLeft(-90).padBottom(23);
        speaker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (speaker.getDrawable() == skin.getDrawable("speaker-high") || speaker.getDrawable() == skin.getDrawable("speaker-medium")) {
                    speaker.setDrawable(skin, "speaker-low");
                    slider.setValue(0);
                } else {
                    speaker.setDrawable(skin, "speaker-high");
                    slider.setValue(0.6f);
                }
                saveSettings();
                loadSettings();
                AudioManager.instance.onSettingsUpdated();
            }
        });

        slider = new Slider(0.0f, 1.0f, 0.01f, false, skin);
        final Container<Slider> container = new Container<Slider>(slider);
        container.setTransform(true);
        container.size(200, 20);
        container.setScale(1.25f);
        tableTools.add(container).padBottom(4).padRight(-25);
        container.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (slider.getValue() > 0.5f) {
                    speaker.setDrawable(skin, "speaker-high");
                } else if (slider.getValue() == 0.0f) {
                    speaker.setDrawable(skin, "speaker-low");
                } else {
                    speaker.setDrawable(skin, "speaker-medium");
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
        slider.setValue(prefs.volMusic);
    }

    private void saveSettings() {
        Preferences prefs = Preferences.instance;
        if (slider.getValue() == 0) {
            prefs.music = false;
        }
        prefs.volMusic = slider.getValue();
        prefs.save();
    }

    public void draw(float deltaTime) {
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }
}
