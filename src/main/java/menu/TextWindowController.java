/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package menu;

public class TextWindowController {

    /**
     * The controller has only one field, the TextView that is created by its constructor according to the filename
     * passed to its constructor.
     * There is no case in which this field should be re-initialized, so it should be made final.
     */
    private final TextView textView;

    TextWindowController(String fileName) {
        this.textView = new TextView(fileName);
    }

    void showTextWindow() {
        textView.showTextWindow();
    }

}
