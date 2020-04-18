/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuButton extends Button {

    private final String FONT_PATH = "main/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url(/main/buttonLong_brown_pressed.png);";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(/main/buttonLong_brown.png);";

    public MenuButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(231);
        setPrefHeight(60);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {

        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 21));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 21));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4); // +-4 is needed because the images differ in height
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });


        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });


    }
}
