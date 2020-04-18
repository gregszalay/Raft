/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GameLabel extends Label {

    private final static String FONT_PATH = "main/kenvector_future.ttf";
    int value;
    private String customText;
    private String defaultValueText;

    public GameLabel() {
        this.defaultValueText = "00";
        this.value = 0;
        setText(" : " + this.defaultValueText);
        setPrefWidth(60);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(4, 4, 4, 4));
        setLabelFont(16);
    }

    public GameLabel(String customText) {
        this();
        this.customText = customText;
        setPrefWidth(60 * customText.length() * 5);
        setText(this.customText + " : " + this.defaultValueText);
    }

    public GameLabel(String customText, double fontSize) {
        this(customText);
        setLabelFont(fontSize);
    }

    private void setLabelFont(double fontSize) {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), fontSize));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", fontSize));
        }
    }

    public void increment() {
        value++;
        setTextValue(value);
    }

    public void decrement() {
        value--;
        setTextValue(value);
    }

    public void increment(int incrementBy) {
        value += incrementBy;
        setTextValue(value);
    }

    public void decrement(int decrementBy) {
        value -= decrementBy;
        setTextValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setTextValue(int value) {
        this.value = value;
        if (customText != null) {
            setText(customText + " : " + value);
        } else {
            setText(" : " + value);
        }
    }
}
