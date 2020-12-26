package com.mygdx.gragdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.HashSet;
import java.util.Set;

public class MenuScreen {
    private final Set<Action> animationActions = new HashSet<Action>();

    public Boolean r = false;

    Button buttonStart;
    Button buttonTools;
    Button buttonCustomization;

    public Button menuButtons(Skin skin, Stage stage, Stage backgroundStage) {
        Gdx.input.setInputProcessor(stage);

        final Image image = new Image(skin, "backgroundTools");
        backgroundStage.addActor(image);


        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.center();

        buttonStart = new Button(skin, "play");
        menuTable.add(buttonStart).row();
        buttonStart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonStart.setVisible(false);
                buttonTools.setVisible(false);
                buttonCustomization.setVisible(false);
                image.setVisible(false);
                r = true;
            }
        });
        buttonAction(buttonStart);

        buttonTools = new Button(skin, "pauseButton");
        menuTable.add(buttonTools).padLeft(-100);
        buttonTools.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        buttonAction(buttonTools);


        buttonCustomization = new Button(skin, "pauseButton");
        menuTable.add(buttonCustomization).padLeft(-110);
        buttonCustomization.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        buttonAction(buttonCustomization);


        stage.addActor(menuTable);

        return buttonStart;
    }
    private void buttonAction(Button button) {
        registerAction(button, Actions.sequence(
                Actions.visible(false),
                Actions.alpha(0, 0.17f),
                Actions.visible(true),
                Actions.alpha(1, 0.17f, Interpolation.exp5Out)));
    }

    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }
}
