/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package settings;

public enum GameSettings {

    PLAYER_RADIUS(15.0),
    SHARK_RADIUS(10.0),
    RAFT_RADIUS(20.0),
    RESOURCE_RADIUS(20.0),

    PLAYER_SPEED(1),
    SHARK_SPEED_NORMAL(0.1),
    SHARK_SPEED_FAST(0.8),
    RESOURCE_SPEED(0.5),

    /**
     * Resources appearing with 32% probability:
     */
    RESOURCES_FREQUENT_AMOUNT(8),

    /**
     * Resources appearing with 4% probability:
     */
    RESOURCES_RARE_AMOUNT(1),

    UTILITIES_MAX_AMOUNT(50),
    RAFT_PANELS_MAX_AMOUNT(50),

    GAME_WIDTH_DEFAULT(1000),
    GAME_HEIGHT_DEFAULT(1000),

    PAUSE_MENU_WIDTH(300),
    PAUSE_MENU_HEIGHT(400),

    MAIN_MENU_WIDTH(500),
    MAIN_MENU_HEIGHT(500),

    TEXT_WINDOW_WIDTH(700),
    TEXT_WINDOW_HEIGHT(500),

    /**
     * Animation is approx. 60 fps, so 2 seconds of game time is approx. 120 loops:
     */
    GAME_LOOPS_PER_ACTION(120),
    GAME_LOOPS_PER_SECOND(60),
    ACTIONS_PER_SPEAR_DEFENCE(5),

    /**
     * Number of player actions before end of game alert
     */
    END_OF_GAME_ACTION_NUMBER(1500);

    private final double value;

    GameSettings(double value) {
        this.value = value;
    }

    public double getDoubleValue() {
        return value;
    }

    public int getIntValue() {
        return (int) value;
    }

}
