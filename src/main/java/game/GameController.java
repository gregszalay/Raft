/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package game;

import components.GameButton;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static settings.ResourcesRequired.*;

public class GameController {

    /**
     * The controller has only one field, the GameView that is passed to its constructor.
     * There is no case in which this field should be re-initialized, so it should be made final.
     */
    private final GameView gameView;

    public GameController(GameView gameView) {
        this.gameView = gameView;
        createListeners();
    }

    /**
     * These EventHandler implementations are responsible for handling mouse events relating to game buttons.
     * If a game button is pressed, the appropriate method of the GameView class is called.
     */
    private void createListeners() {
        assignClickHandler(gameView.getRaftPanelButton(), this::raftButtonClicked);
        assignClickHandler(gameView.getCampfireButton(), this::campfireButtonClicked);
        assignClickHandler(gameView.getWaterPurifierButton(), this::waterpurifierButtonClicked);
        assignClickHandler(gameView.getFishingNetButton(), this::fishingnetButtonClicked);
        assignClickHandler(gameView.getSpearsButton(), this::spearButtonClicked);
        assignClickHandler(gameView.getCookFishButton(), this::cookFishButtonClicked);
        assignClickHandler(gameView.getCookPotatoesButton(), this::cookPotatoesButtonClicked);
        /*
         * If the user attempts to close the game window, the quitApp() method of the PauseSubscene class of the gameview
         * object is invoked. The quitApp() method should be responsible for warning the user to save the game.
         */
        gameView.getGameStage().setOnCloseRequest(event -> gameView.getPauseSubScene().quitApp());
        createGameKeyListeners();
    }

    private void assignClickHandler(GameButton gameButton, Runnable runnable) {

        gameButton.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                gameButton.setButtonPressedStyle();
                runnable.run();
            }
        });

        gameButton.setOnMouseReleased(mouseEvent -> {
            gameButton.setButtonReleasedStyle();
        });

        gameButton.setOnMouseEntered(mouseEvent -> {
            gameButton.setEffect(new DropShadow());
        });

        gameButton.setOnMouseExited(mouseEvent -> {
            gameButton.setEffect(null);
        });

    }


    private void raftButtonClicked() {
        gameView.build(RAFT_PANEL);
    }

    private void campfireButtonClicked() {
        gameView.build(CAMPFIRE);
    }

    private void waterpurifierButtonClicked() {
        gameView.build(WATERPURIFIER);
    }

    private void fishingnetButtonClicked() {
        gameView.build(FISHING_NET);
    }

    private void spearButtonClicked() {
        gameView.build(SPEAR);
    }

    private void cookFishButtonClicked() {
        gameView.setFishCooking(true);
    }

    private void cookPotatoesButtonClicked() {
        gameView.setPotatoCooking(true);
    }


    /**
     * These EventHandler implementations are responsible for controlling the player in the game, initiate spear defence,
     * and also to pause or save the game if the appropriate key combination is pressed.
     * <p>
     * If an arrow key is pressed on the keyboard, this sets the value of the appropriate boolean field of the GameView
     * object which then affects the game logic (i.e. the movement of the player).
     * <p>
     * The EventHandler implementations are added as filters so child nodes will not receive these events.
     */
    private void createGameKeyListeners() {

        EventHandler<KeyEvent> filterKeyPressed = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                leftKeyPressed();
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                rightKeyPressed();
            } else if (keyEvent.getCode() == KeyCode.UP) {
                upKeyPressed();
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                downKeyPressed();
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                escapeKeyPressed();
            } else if (keyEvent.isControlDown() && keyEvent.getCode().equals(KeyCode.S)) {
                controlSKeyPressed();
            } else if (keyEvent.getCode() == KeyCode.N) {
                nKeyPressed();
            }
        };
        gameView.getGameScene().addEventFilter(KeyEvent.KEY_PRESSED, filterKeyPressed);

        EventHandler<KeyEvent> filterKeyReleased = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                leftKeyReleased();
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                rightKeyReleased();
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                downKeyReleased();
            } else if (keyEvent.getCode() == KeyCode.UP) {
                upKeyReleased();
            } else if (keyEvent.getCode() == KeyCode.N) {
                nKeyReleased();
            }
        };
        gameView.getGameScene().addEventFilter(KeyEvent.KEY_RELEASED, filterKeyReleased);

    }

    private void leftKeyPressed() {
        gameView.setLeftKeyPressed(true);
    }

    private void rightKeyPressed() {
        gameView.setRightKeyPressed(true);
    }

    private void downKeyPressed() {
        gameView.setDownKeyPressed(true);
    }

    private void upKeyPressed() {
        gameView.setUpKeyPressed(true);
    }

    private void leftKeyReleased() {
        gameView.setLeftKeyPressed(false);
    }

    private void rightKeyReleased() {
        gameView.setRightKeyPressed(false);
    }

    private void downKeyReleased() {
        gameView.setDownKeyPressed(false);
    }

    private void upKeyReleased() {
        gameView.setUpKeyPressed(false);
    }



    private void escapeKeyPressed() {
        gameView.pauseGame();
    }

    private void controlSKeyPressed() {
        gameView.getPauseSubScene().saveGame();
    }

    private void nKeyPressed() {
        gameView.spearDefence();
    }

    private void nKeyReleased() {
        gameView.endSpearDefence();
    }


}
