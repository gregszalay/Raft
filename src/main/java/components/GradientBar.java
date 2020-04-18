/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import data.GameImages;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class GradientBar extends SubScene {

    private ImageView gradientBar;
    private double step;

    public GradientBar() {
        super(new AnchorPane(), 120, 40);
        setStyle();
        gradientBar = new ImageView(GameImages.GRADIENT_BAR.getLargeUrl());
        getPane().getChildren().add(gradientBar);
        this.step = (gradientBar.getImage().getWidth() - 120) / 100;
        setToFull();

    }

    private void setStyle() {
        prefWidth(120);
        prefHeight(40);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        setLayoutX(400);
        setLayoutY(600);
    }

    public void setToFull() {
        gradientBar.setLayoutX(120 + 1 - gradientBar.getImage().getWidth());
    }

    public void setToEmpty() {
        gradientBar.setLayoutX(-1);
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    public void decrement() {
        gradientBar.setLayoutX(gradientBar.getLayoutX() + step);
    }

    public void decrement(int decrementBy) {
        gradientBar.setLayoutX(gradientBar.getLayoutX() + step * decrementBy);
    }

    public void increment() {
        gradientBar.setLayoutX(gradientBar.getLayoutX() - step);
    }

    public void increment(int incrementBy) {
        gradientBar.setLayoutX(gradientBar.getLayoutX() - step * incrementBy);
    }


}
