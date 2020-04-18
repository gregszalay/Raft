/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package menu;

import components.CustomAlert;
import components.GameLogo;
import components.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static data.GameImages.BACKGROUND_300_400;
import static data.GameImages.RAFT_LOGO;
import static settings.GameSettings.MAIN_MENU_HEIGHT;
import static settings.GameSettings.MAIN_MENU_WIDTH;

public class MenuView {

    private final AnchorPane mainPane;
    private final Stage mainStage;

    private GameLogo logo;

    private MenuButton newGameButton;
    private MenuButton loadGameButton;
    private MenuButton descriptionButton;
    private MenuButton creditsButton;
    private MenuButton exitButton;

    public MenuView() {
        this.mainPane = new AnchorPane();
        this.mainStage = new Stage();
        mainStage.setScene(new Scene(mainPane, MAIN_MENU_WIDTH.getDoubleValue(), MAIN_MENU_HEIGHT.getDoubleValue()));
        createBackground();
        createButtons();
        createLogo();
        createBoxes();
    }

    private void createBackground() {
        Image backgroundImage = new Image(BACKGROUND_300_400.getLargeUrl(), 550, 550, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createButtons() {
        newGameButton = new MenuButton("ÚJ JÁTÉK");
        loadGameButton = new MenuButton("MENTETT JÁTÉK");
        descriptionButton = new MenuButton("SZABÁLYOK");
        creditsButton = new MenuButton("KÖSZÖNET");
        exitButton = new MenuButton("KILÉPÉS");
    }

    private void createLogo() {
        logo = new GameLogo(RAFT_LOGO.getLargeUrl());
        mainPane.getChildren().add(logo);
    }

    private void createBoxes() {
        VBox pauseButtonsBox = new VBox(newGameButton, loadGameButton, descriptionButton, creditsButton, exitButton);
        pauseButtonsBox.setAlignment(Pos.CENTER);
        pauseButtonsBox.setSpacing(7);
        VBox parentBox = new VBox(logo, pauseButtonsBox);
        parentBox.setAlignment(Pos.CENTER);
        parentBox.setSpacing(7);
        AnchorPane.setRightAnchor(parentBox, 0.0);
        AnchorPane.setBottomAnchor(parentBox, 0.0);
        AnchorPane.setTopAnchor(parentBox, 0.0);
        AnchorPane.setLeftAnchor(parentBox, 0.0);
        mainPane.getChildren().add(parentBox);
    }


    public void noSaveAlert() {
        CustomAlert noSaveAlert = new CustomAlert(Alert.AlertType.INFORMATION);
        noSaveAlert.setTitle("Betültés sikertelen");
        noSaveAlert.setHeaderText("Nincs mentett játék!");
        noSaveAlert.setContentText("Nem találtunk korábbi mentett játékot!");
        noSaveAlert.show();

    }

    public Stage getMainStage() {
        return mainStage;
    }

    public MenuButton getNewGameButton() {
        return newGameButton;
    }

    public MenuButton getLoadGameButton() {
        return loadGameButton;
    }

    public MenuButton getDescriptionButton() {
        return descriptionButton;
    }

    public MenuButton getCreditsButton() {
        return creditsButton;
    }

    public MenuButton getExitButton() {
        return exitButton;
    }


}


