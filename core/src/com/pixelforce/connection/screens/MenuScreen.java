package com.pixelforce.connection.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.pixelforce.connection.util.Constants;
import com.pixelforce.connection.util.levels.Levels;

import java.util.HashSet;
import java.util.Set;

public class MenuScreen {
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    private final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private final Set<Action> animationActions = new HashSet<>();

    private final OptionsMenu optionsMenu;

    Skin skin;

    Label buttonStart;
    Label buttonOptions;
    Label buttonNextLevel;
    Label buttonReplay;
    Label buttonHome;

    public Boolean start = false;
    static public Boolean completedLevel;
    Boolean hideStatistics = false;
    float delay = 3.5f;


    public MenuScreen() {
        Gdx.input.setInputProcessor(stage);
        optionsMenu = new OptionsMenu();

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));
    }


    public void setupMenu() {
        final Image background = new Image(skin, "background");
        backgroundStage.addActor(background);


        final Table table = new Table();
        table.setFillParent(true);
        table.center();

        buttonStart = new Label("Play", skin, "Roboto-Bold");
        table.add(buttonStart).row();
        buttonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                background.setVisible(false);
                start = true;
            }
        });

        buttonOptions = new Label("Options", skin, "Roboto-Thin");
        table.add(buttonOptions);
        buttonOptions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                optionsMenu.setupOptions(stage);
            }
        });


        buttonAction(table, 0.35f);
        stage.addActor(table);
    }

    public void setupLevelEnd() {
        Gdx.input.setInputProcessor(backgroundStage);
        delay = 3.5f;
        hideStatistics = false;
        String text = "Completed";
        String font = "Roboto-Thin-Scaled-2";
        if (!completedLevel) {
            text = "Failed";
            font = "Roboto-Bold-Scaled-2";
        }


        final Image background = new Image(skin, "background");
        backgroundStage.addActor(background);


        final Table labelTable = new Table();
        labelTable.setFillParent(true);
        labelTable.center();

        Label label1 = new Label("Level", skin, "Roboto-Thin");
        labelTable.add(label1).padBottom(500);
        Label label2 = new Label(text, skin, "Roboto-Bold");
        labelTable.add(label2).padBottom(500).padLeft(15);

        buttonAction(labelTable, 0.10f);
        stage.addActor(labelTable);


        final Table statsTable = new Table();
        statsTable.setFillParent(true);
        statsTable.center();
        final Levels prefs = Levels.instance;
        prefs.load();

        final Label levelLabel = new Label("Level  " + prefs.level + " /" + Levels.levelStats, skin, "Roboto-Thin-Scaled-2");
        statsTable.add(levelLabel).padBottom(25).row();
        final Label stageLabel = new Label("Stage  " + prefs.stage + " /" + "6", skin, "Roboto-Thin-Scaled-2");
        statsTable.add(stageLabel);

        buttonAction(statsTable, 0.25f);
        stage.addActor(statsTable);


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                background.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tableMenu(background, labelTable, statsTable);
                        hideStatistics = true;
                    }
                });
            }
        }, 1.8f);


        buttonNextLevel = new Label("Next Level", skin, "Roboto-Bold-Scaled-2");
        buttonReplay = new Label("Replay", skin, font);
        buttonHome = new Label("Home", skin, "Roboto-Thin-Scaled-2");


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (completedLevel) {
                    prefs.load();
                    int level2 = prefs.level + 1;
                    final int stage2 = prefs.stage + 1;

                    if (level2 == Levels.levelStats + 1) {
                        registerAction(statsTable, Actions.sequence(
                                Actions.alpha(0, 0.17f),
                                Actions.alpha(1, 0.17f, Interpolation.exp5Out)));
                        levelLabel.setText("Level  " + 1 + " /" + Levels.levelStats2);
                        stageLabel.setText("Stage  " + stage2 + " /" + "6");
                        delay = 6;
                    } else
                        levelLabel.setText("Level  " + level2 + " /" + Levels.levelStats);
                }
            }
        }, 1.25f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!hideStatistics) tableMenu(background, labelTable, statsTable);
            }
        }, delay);
    }

    private void tableMenu(final Image background, final Table labelTable, final Table statsTable) {
        Gdx.input.setInputProcessor(stage);

        final Table table = new Table();
        table.setFillParent(true);
        table.center();

        if (completedLevel) {
            table.add(buttonNextLevel).row();
            buttonNextLevel.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    background.setVisible(false);
                    labelTable.setVisible(false);
                    table.setVisible(false);
                }
            });
        }

        if (!completedLevel) table.padBottom(25);
        table.add(buttonReplay).row();
        buttonReplay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                background.setVisible(false);
                labelTable.setVisible(false);
                table.setVisible(false);
            }
        });

        table.add(buttonHome);
        buttonHome.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                background.setVisible(false);
                labelTable.setVisible(false);
                table.setVisible(false);
                setupMenu();
            }
        });

        statsTable.setVisible(false);
        buttonAction(table, 0.10f);
        stage.addActor(table);
    }

    private void buttonAction(Table table, float actionsDelay) {
        registerAction(table, Actions.sequence(
                Actions.visible(false),
                Actions.alpha(0, 0.17f),
                Actions.delay(actionsDelay),
                Actions.visible(true),
                Actions.alpha(1, 0.17f, Interpolation.exp5Out)));
    }

    private void registerAction(Actor actor, Action action) {
        animationActions.add(action);
        actor.addAction(action);
    }


    public void draw(float deltaTime) {
        backgroundStage.getViewport().apply();
        backgroundStage.act(deltaTime);
        backgroundStage.draw();
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
        optionsMenu.draw(deltaTime);
    }

    public void resize(int width, int height) {
        backgroundStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        optionsMenu.resize(width, height);
    }

    public void dispose() {
        backgroundStage.dispose();
        stage.dispose();
        skin.dispose();
        optionsMenu.dispose();
    }
}
