package com.pixelforce.connection.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.pixelforce.connection.screens.MenuScreen;
import com.pixelforce.connection.util.Constants;
import com.pixelforce.connection.util.levels.FiveByFive;
import com.pixelforce.connection.util.levels.FourByFour;
import com.pixelforce.connection.util.levels.Levels;
import com.pixelforce.connection.util.levels.SixBySix;

public class Game {
    private final Stage backgroundStage = new Stage(new FillViewport(516, 684));
    public final Stage stage = new Stage(new ExtendViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

    private Skin skinGame;
    private final Skin skinMenu;

    private final Fields fields;
    private final Levels levels;
    private final MenuScreen menuScreen;
    private final FourByFour fourByFour = new FourByFour();
    private final FiveByFive fiveByFive = new FiveByFive();
    private final SixBySix sixBySix = new SixBySix();

    public Label timerText;
    public Label roundsText;
    Image[] field = new Image[Fields.quantity + 1];
    public Label restartButton;

    public Boolean endlevel = false;
    Boolean touched = false;
    public int backButtonClicks = 0;
    int[] start1 = new int[3];
    int start2 = 1;
    int lastField;
    int rounds = 0;
    public int milliseconds = 0;
    public int seconds = 0;
    int lastField2;
    boolean entry = true;

    int[] fm = new int[10];


    public Game() {
        fields = new Fields();
        levels = new Levels();
        menuScreen = new MenuScreen();

        skinMenu = new Skin(
                Gdx.files.internal(Constants.SKIN_MENU_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_MENU_UI));

        levels.Level();
    }


    public void setupGame(Boolean restartTimer, Boolean restart, Boolean genr) {
        if (!genr) {
            levels.Level();
            skinGame = new Skin(
                    Gdx.files.internal(Constants.SKIN_GAME_UI),
                    new TextureAtlas(Constants.TEXTURE_ATLAS_GAME_UI));

            field = new Image[Fields.quantity + 1];
            fields.reset();

            backgroundStage.clear();
            Image background = new Image(skinGame, "background");
            backgroundStage.addActor(background);


            Table timerTable = new Table();
            timerTable.setFillParent(true);
            timerTable.top();
            timerText = new Label("", skinMenu, "Roboto-Thin");
            timerTable.add(timerText).padTop(10);
            stage.addActor(timerTable);

            Table roundsTable = new Table();
            roundsTable.setFillParent(true);
            roundsTable.top();
            roundsText = new Label("", skinMenu, "Roboto-Thin");
            roundsTable.add(roundsText).padTop(70);
            stage.addActor(roundsTable);


            Table gameTable = new Table();
            gameTable.setFillParent(true);
            gameTable.center();
            int row = Fields.row - 1;
            for (int i = 0; i < Fields.quantity; i++) {
                field[i] = new Image(skinGame, "field-dn");
                fields.setCheck(i, field[i], true, "Check0");
                gameTable.add(field[i]);
                if (i == row) {
                    row = row + Fields.row;
                    gameTable.row();
                }
            }
            stage.addActor(gameTable);


            Table restartTable = new Table();
            restartTable.setFillParent(true);
            restartTable.bottom();
            restartButton = new Label("", skinMenu, "Roboto-Thin-Scaled");
            restartTable.add(restartButton).padBottom(20);
            restartButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    stage.clear();
                    setupGame(false, false, false);
                }
            });
            stage.addActor(restartTable);
        }


        if (entry) {
            fourByFour.create();
            sixBySix.create();
            fiveByFive.create();
            entry = false;
        } else if (genr) {
            if (Levels.levelName.equals("SixBySix"))
                sixBySix.create();
            if (Levels.levelName.equals("FiveByFive"))
                fiveByFive.create();
            if (Levels.levelName.equals("FourByFour"))
                fourByFour.create();
        }


        if (restart)
            switch (Levels.levelName) {
                case "SixBySix":
                    System.arraycopy(sixBySix.pick(rounds), 0, fm, 0, 10);
                    break;
                case "FiveByFive":
                    System.arraycopy(fiveByFive.pick(rounds), 0, fm, 0, 10);
                    break;
                case "FourByFour":
                    System.arraycopy(fourByFour.pick(rounds), 0, fm, 0, 8);
                    break;
            }


        if (!genr) {
            fields.reset();
            int gg = 0;
            for (int i = 0; i < 5; i++) {
                String color = "Check1";
                switch (i) {
                    case 1:
                        color = "Check2";
                        break;
                    case 2:
                        color = "Check3";
                        break;
                    case 3:
                        color = "Check4";
                        break;
                    case 4:
                        color = "Check5";
                        break;
                }

                for (int t = 1; t <= 2; t++)
                    if (i != 4 || !Levels.levelName.equals("FourByFour")) {
                        fields.setStartingField(fm[gg]);
                        fields.setCheck(fm[gg], field[fm[gg]], true, color);
                        gg++;
                    }
            }

            if (restartTimer) {
                seconds = levels.timer;
                milliseconds = 0;
            }
        }
    }


    public void update() {
        Gdx.input.setInputProcessor(stage);
        if (milliseconds == 0) {
            seconds--;
            milliseconds = 99;
        }
        milliseconds--;
        String strMilliseconds = String.valueOf(milliseconds);
        String strSeconds = String.valueOf(seconds);
        if (milliseconds < 10) {
            strMilliseconds = "0" + milliseconds;
        }
        if (seconds < 10) {
            strSeconds = "0" + seconds;
        }
        timerText.setText(strSeconds + "." + strMilliseconds);
        if (seconds == 0 && milliseconds == 0) {
            MenuScreen.completedLevel = false;
            menuScreen.start = false;
            endlevel = true;
            rounds = 0;
        }
        roundsText.setText(rounds + 1 + " /" + levels.rounds);
        restartButton.setText("RESTART");


        if (Gdx.input.isTouched()) {
            for (int i = 0; i < Fields.quantity; i++)
                if (fields.getStartingField(i)) {
                    Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                    Vector2 mouseLocalPosition = field[i].screenToLocalCoordinates(mouseScreenPosition);
                    if (field[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                        start1[start2] = i;
                        if (start2 == 2) {
                            start2 = 1;
                        }
                        start2++;
                        touched = true;
                    }
                }
        } else {
            touched = false;
            for (int i = 0; i < 3; i++) {
                start1[i] = 0;
            }
            start2 = 1;
            lastField = 0;
        }


        if (touched)
            for (int i = 0; i < Fields.quantity; i++) {
                Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Vector2 mouseLocalPosition = field[i].screenToLocalCoordinates(mouseScreenPosition);
                if (field[i].hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
                    if (lastField != i || (i == 0 && fields.getStartingField(0))) {

                        if (fields.getColor(i).equals("Check0") && fields.getConnected(fields.getColor(lastField))) {

                        } else if (fields.getColor(i).equals("Check0")) {
                            if (lastField == i - 1 || lastField == i + 1 || lastField == i - Fields.row || lastField == i + Fields.row) {
                                fields.setCheck(i, field[i], false, "Check0");
                                fields.setCheck(i, field[i], true, fields.getColor(lastField));
                            }

                        } else if (fields.getConnected(fields.getColor(i))) {
                            start2 = 1;
                            lastField = 0;
                            clear();

                        } else if (fields.getStartingField(i) && start1[1] != start1[2] && fields.getColor(i).equals(fields.getColor(lastField))) {
                            if (lastField != i - 1 && lastField != i + 1 && lastField != i - Fields.row && lastField != i + Fields.row) {
                                start2 = 1;
                                lastField = 0;
                                clear();
                            }
                            fields.setConnected(i, fields.getColor(i), true);

                        } else if (fields.getStartingField(i)) {
                            start2 = 1;
                            lastField = 0;
                            clear();
                            fields.setConnected(i, fields.getColor(i), true);

                        } else if (fields.getCheck(i, fields.getColor(i)) && !fields.getStartingField(lastField)) {
                            fields.setCheck(lastField, field[lastField], false, fields.getColor(lastField));
                            fields.setCheck(lastField, field[lastField], true, "Check0");
                            if (fields.getConnected(fields.getColor(0)) && fields.getCheck(0, fields.getColor(0))) {
                                fields.setCheck(0, field[0], false, "Check0");
                                fields.setCheck(0, field[0], true, fields.getColor(0));
                            }
                            if (fields.getColor(lastField2).equals(fields.getColor(i)) && i != lastField2) {
                                start2 = 1;
                                lastField = 0;
                                clear();
                            }
                        }

                        lastField2 = lastField;
                        lastField = i;
                    }
                }
            }

        if (!touched) {
            clear();
            if (fields.getConnected("Check1") && fields.getConnected("Check2") && fields.getConnected("Check3") && fields.getConnected("Check4"))
                if (fields.getConnected("Check5") || Levels.levelName.equals("FourByFour")) {
                    rounds++;
                    if (rounds == levels.rounds) {
                        MenuScreen.completedLevel = true;
                        menuScreen.start = false;
                        endlevel = true;
                        rounds = 0;
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                setupGame(false, false, true);
                            }
                        }, 1.30f);

                    } else {
                        stage.clear();
                        setupGame(false, true, false);
                    }
                }
        }
    }

    private void clear() {
        for (int i = 0; i < Fields.quantity; i++)
            if (!fields.getStartingField(i))
                if (!fields.getConnected(fields.getColor(i))) {
                    fields.setCheck(i, field[i], false, "Check1");
                    fields.setCheck(i, field[i], false, "Check2");
                    fields.setCheck(i, field[i], false, "Check3");
                    fields.setCheck(i, field[i], false, "Check4");
                    if (!Levels.levelName.equals("FourByFour")) {
                        fields.setCheck(i, field[i], false, "Check5");
                    }
                    fields.setCheck(i, field[i], true, "Check0");
                    fields.i1 = 1;
                    fields.i2 = 1;
                    fields.i3 = 1;
                    fields.i4 = 1;
                    if (!Levels.levelName.equals("FourByFour")) {
                        fields.i5 = 1;
                    }
                }
    }


    public void draw(float deltaTime) {
        backgroundStage.getViewport().apply();
        backgroundStage.act(deltaTime);
        backgroundStage.draw();
        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }

    public void resize(int width, int height) {
        backgroundStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        backgroundStage.dispose();
        stage.dispose();
        skinGame.dispose();
        skinMenu.dispose();
    }
}
