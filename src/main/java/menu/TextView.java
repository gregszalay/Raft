/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package menu;

import data.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static data.GameImages.BACKGROUND_320_420;
import static settings.GameSettings.TEXT_WINDOW_HEIGHT;
import static settings.GameSettings.TEXT_WINDOW_WIDTH;

public class TextView {

    private final String FONT_PATH = "main/kenvector_future.ttf";

    private Stage textStage;
    private Scene textScene;
    private Label label;
    private ScrollBar scrollBar;
    private VBox contentBox;

    private double height;
    private double width;

    private String fileName;

    public TextView(String textFileName) {
        width = TEXT_WINDOW_WIDTH.getDoubleValue();
        height = TEXT_WINDOW_HEIGHT.getDoubleValue();
        fileName = textFileName;
        initializeStage(width, height);
        createListeners();
    }

    private void initializeStage(double width, double height) {
        this.width = width;
        this.height = height;
        Group root = new Group();
        textScene = new Scene(root, width, height);
        textStage = new Stage();
        textStage.setScene(textScene);
        createContentBox();
        createScrollBar();
        root.getChildren().addAll(contentBox, scrollBar);
    }

    public void showTextWindow() {
        textStage.show();
    }

    private void setContentBoxBackground() {
        Image backgroundImage = new Image(BACKGROUND_320_420.getLargeUrl(), contentBox.getMaxWidth(), contentBox.getMaxHeight(),
                false,
                true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        contentBox.setBackground(new Background(background));

    }

    private void createLabel() {
        String contents = "";
        try {
            contents = FileUtils.getTextFileContents(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.label = new Label(contents);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setAlignment(Pos.TOP_CENTER);
        label.setWrapText(true);
        label.setOpacity(0.4);
        try {
            label.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 15));
        } catch (FileNotFoundException e) {
            label.setFont(Font.font("Verdana", 21));
        }
        label.setMaxWidth(width - 20);
        label.setPrefSize(width - 20, 2500);

    }

    private void createContentBox() {
        createLabel();
        contentBox = new VBox();
        contentBox.setLayoutX(5);
        contentBox.setSpacing(10);
        contentBox.setPrefSize(width, 2500);
        contentBox.getChildren().add(label);
        setContentBoxBackground();
    }

    private void createScrollBar() {
        scrollBar = new ScrollBar();
        scrollBar.setLayoutX(textScene.getWidth() - scrollBar.getWidth());
        scrollBar.setMin(0);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setPrefHeight(height);
        scrollBar.setMax(1500);
        scrollBar.adjustValue(label.getHeight());
        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                contentBox.setLayoutY(-new_val.doubleValue());
            }
        });

    }

    private void createListeners() {
        textStage.maximizedProperty().addListener((isMaximised) -> {
            System.out.println("maximized:" + isMaximised);
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            double height = Screen.getPrimary().getVisualBounds().getHeight();
            textStage.close();
            initializeStage(width, height);
            textStage.show();
        });

    }


}
