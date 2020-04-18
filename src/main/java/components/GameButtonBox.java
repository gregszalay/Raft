/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import data.GameImages;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GameButtonBox extends HBox {

    private GameButtonBox() {
        super();
        setCustomBackground(GameImages.BACKGROUND_BEIGE_SMALL.getLargeUrl(), 130, 75);
        setStyle();
    }

    public GameButtonBox(GameLabel label) {
        this();
        this.getChildren().add(label);
    }

    public GameButtonBox(ImageView iconImage, GameLabel label) {
        this();
        this.getChildren().addAll(iconImage, label);
    }

    public GameButtonBox(GameButton button, GameLabel label) {
        this();
        this.getChildren().addAll(button, label);
    }

    public GameButtonBox(ImageView iconImage1, ImageView iconImage2, ImageView iconImage3) {
        this();
        this.getChildren().addAll(iconImage1, iconImage2, iconImage3);
    }

    public void setCustomBackground(String imageUrl, double imageWidth, double imageHeight) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(imageUrl, imageWidth, imageHeight, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        this.setBackground(new Background(backgroundImage));
    }

    private void setStyle() {
        setPrefSize(130, 75);
        setPrefSize(130, 75);
        setAlignment(Pos.CENTER);
        setSpacing(2);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
    }


}
