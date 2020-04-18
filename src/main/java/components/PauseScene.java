/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import data.SaveLoad;
import game.GameView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

import static data.GameImages.BACKGROUND_300_400;
import static settings.GameSettings.PAUSE_MENU_HEIGHT;
import static settings.GameSettings.PAUSE_MENU_WIDTH;

public class PauseScene extends SubScene {

    private final Stage menuStage;
    private final Stage gameStage;
    private final GameView gameView;

    private MenuButton continueButton;
    private MenuButton saveButton;
    private MenuButton mainMenuButton;
    private MenuButton quitButton;

    private boolean isGameSaved;

    private AnimationTimer gameTimer;


    public PauseScene(GameView gameView, Stage menuStage, Stage gameStage,
                      AnimationTimer gameTimer) {
        super(new AnchorPane(), PAUSE_MENU_WIDTH.getDoubleValue(), PAUSE_MENU_HEIGHT.getDoubleValue());
        this.gameView = gameView;
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        this.gameTimer = gameTimer;
        setStyle();
        createBackground();
        createButtons();
        createBoxes();
        createKeyListeners();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    private void setStyle() {
        prefWidth(PAUSE_MENU_WIDTH.getDoubleValue());
        prefHeight(PAUSE_MENU_HEIGHT.getDoubleValue());
        AnchorPane root2 = (AnchorPane) this.getRoot();
        setLayoutX(400);
        setLayoutY(600);
    }


    private void createBoxes() {
        VBox pauseButtonsBox = new VBox(continueButton, saveButton, mainMenuButton, quitButton);
        pauseButtonsBox.setAlignment(Pos.CENTER);
        pauseButtonsBox.setSpacing(7);
        AnchorPane.setRightAnchor(pauseButtonsBox, 0.0);
        AnchorPane.setBottomAnchor(pauseButtonsBox, 0.0);
        AnchorPane.setTopAnchor(pauseButtonsBox, 0.0);
        AnchorPane.setLeftAnchor(pauseButtonsBox, 0.0);
        getPane().getChildren().add(pauseButtonsBox);
    }


    private void createBackground() {
        Image backgroundImage = new Image(BACKGROUND_300_400.getLargeUrl(), 300, 400, false,
                true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.getPane().setBackground(new Background(background));
    }

    private void createButtons() {
        continueButton = new MenuButton("FOLYTATÁS");
        createButtonListeners(continueButton);
        saveButton = new MenuButton("MENTÉS");
        createButtonListeners(saveButton);
        mainMenuButton = new MenuButton("FŐMENÜ");
        createButtonListeners(mainMenuButton);
        quitButton = new MenuButton("KILÉPÉS");
        createButtonListeners(quitButton);
    }

    private void createButtonListeners(MenuButton menuButton) {

        menuButton.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (menuButton == continueButton) {
                    continueGame();
                } else if (menuButton == saveButton) {
                    saveGame();
                } else if (menuButton == mainMenuButton) {
                    exitToMainMenu();
                } else if (menuButton == quitButton) {
                    quitApp();
                }
                mouseEvent.consume();
            }
        });

        menuButton.setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                mouseEvent.consume();
            }
        });

    }

    private void continueGame() {
        gameView.continueGame();
    }

    public void saveGame() {
        SaveLoad.INSTANCE.createSaveSlot(gameView);
        saveConfirmedAlert();
        isGameSaved = true;
    }

    private void exitToMainMenu() {
        if (isGameSaved || isProgressLossConfirmedAlert()) {
            gameStage.close();
            menuStage.show();
        }
    }

    public void quitApp() {
        if (isGameSaved || isProgressLossConfirmedAlert()) {
            gameStage.close();
        }
    }

    private boolean isProgressLossConfirmedAlert() {
        CustomAlert menuAlert = new CustomAlert(Alert.AlertType.CONFIRMATION);
        menuAlert.setTitle("Kilépés");
        menuAlert.setHeaderText("Kilépés a játékból");
        menuAlert.setContentText("A mentetlen eredmények elvesznek!");
        Optional<ButtonType> result = menuAlert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            menuAlert.close();
            return true;
        } else {
            System.out.println("Exit was aborted");
            menuAlert.close();
            return false;
        }
    }

    private void saveConfirmedAlert() {
        CustomAlert gameSaveConfirmAlert = new CustomAlert(Alert.AlertType.INFORMATION);
        gameSaveConfirmAlert.setTitle("Mentés");
        gameSaveConfirmAlert.setHeaderText("Mentés Sikeres!");
        gameSaveConfirmAlert.setContentText("Elmentettük eddigi eredményeidet!");
        gameSaveConfirmAlert.show();

    }

    private void createKeyListeners() {
        EventHandler<KeyEvent> filterKeyPressed = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                continueGame();
            }
        };
        this.addEventFilter(KeyEvent.KEY_PRESSED, filterKeyPressed);

    }


}
