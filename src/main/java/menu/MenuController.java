/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package menu;

import game.GameController;
import game.GameView;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class MenuController {

    /**
     * The controller has only one field, the MenuView that is passed to its constructor.
     * There is no case in which this field should be re-initialized, so it should be made final.
     */
    private final MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
        createListeners();
    }

    private void createListeners() {
        assignClickHandler(menuView.getNewGameButton(), this::newGameButtonClicked);
        assignClickHandler(menuView.getLoadGameButton(), this::loadGameButtonClicked);
        assignClickHandler(menuView.getDescriptionButton(), this::descriptionButtonClicked);
        assignClickHandler(menuView.getCreditsButton(), this::creditsButtonClicked);
        assignClickHandler(menuView.getExitButton(), this::exitButtonClicked);
    }

    private void assignClickHandler(Button button, Runnable runnable) {
        button.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                runnable.run();
            }
        });
    }

    /**
     * If the "Exit" button of the menuView object is pressed, the stage closes and the application stops.
     */
    private void exitButtonClicked() {
        menuView.getMainStage().close();
    }

    /**
     * If the "Credits" button of the menuView object is pressed, a new text window is created (see step (1) and (2) in the javadoc in Main.java)
     * The new TextView's showTextWindow() method is called.
     */
    private void creditsButtonClicked() {
        new TextWindowController("credits").showTextWindow();
    }

    /**
     * If the "Description" button of the menuView object is pressed, a new text window is created (see step (1) and (2) in the javadoc in Main.java)
     * The new TextView's showTextWindow() method is called.
     */
    private void descriptionButtonClicked() {
        new TextWindowController("description").showTextWindow();
    }

    /**
     * If the "New Game" button of the menuView object is pressed, a new game is created (see step (1) and (2) in the javadoc in Main.java)
     * The new GameView object's startGame() method is called and the main Stage of the menuView is passed to it.
     */
    private void newGameButtonClicked() {
        GameView game = new GameView();
        new GameController(game);
        game.startGame(menuView.getMainStage());
    }

    /**
     * If the "Load Game" button of the menuView object is pressed, a new game is created (see step (1) and (2) in the javadoc in Main.java)
     * The new GameView object's loadGame() method is called, then the startGame() method is called same as above.
     */
    private void loadGameButtonClicked() {
        GameView game = new GameView();
        new GameController(game);
        try {
            game.loadGame();
            game.startGame(menuView.getMainStage());
        } catch (FileNotFoundException e) {
            menuView.noSaveAlert();
        }
    }
}
