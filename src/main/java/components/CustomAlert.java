/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import data.GameImages;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class CustomAlert extends Alert {

    public CustomAlert(AlertType alertType) {
        super(alertType);
        setCustomBackground(GameImages.BACKGROUND_300_400.getLargeUrl());
    }

    private void setCustomBackground(String imageUrl) {
        Image image = new Image(imageUrl, this.getDialogPane().getWidth(), this.getDialogPane().getHeight(), true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        this.getDialogPane().setBackground(new Background(backgroundImage));
    }

}
