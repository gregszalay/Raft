/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameButton extends Button {

    private final String FONT_PATH = "main/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url(/main/buttonSquare_brown_pressed.png);";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(/main/buttonSquare_brown.png);";
    private ImageView buttonIcon;

    private GameButton() {
        setPrefWidth(55);
        setPrefHeight(60);
        setStyle(BUTTON_FREE_STYLE);
    }

    public GameButton(ImageView buttonIcon, String toolTipText) {
        this();
        this.buttonIcon = buttonIcon;
        this.setTooltip(new Tooltip(toolTipText));
        setGraphic(this.buttonIcon);
    }

    public void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(55);
        setLayoutY(getLayoutY() + 5); // this is needed because the images differ in height
        Image resizedImage = new Image(this.buttonIcon.getImage().getUrl(), this.buttonIcon.getImage().getWidth(),
                this.buttonIcon.getImage().getHeight() - 1, true, true);
        setGraphic(new ImageView(resizedImage));

    }

    public void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(60);
        setLayoutY(getLayoutY() - 5); // this is needed because the images differ in height
        Image resizedImage = new Image(this.buttonIcon.getImage().getUrl(), this.buttonIcon.getImage().getWidth(),
                this.buttonIcon.getImage().getHeight() + 1, true, true);
        setGraphic(new ImageView(resizedImage));

    }

}
