/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package main;

import javafx.application.Application;
import javafx.stage.Stage;
import menu.MenuController;
import menu.MenuView;

/**
 * The main flow of the program:
 * (1) a new instance of a view class is created,
 * (2) a new instance of a controller class is created and the view object is passed to the controller's constructor,
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MenuView mainMenuView = new MenuView();
        new MenuController(mainMenuView);
        primaryStage = mainMenuView.getMainStage();
        primaryStage.show();
    }
}
