/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package data;

import javafx.scene.image.ImageView;

public enum GameImages {

    BACKGROUND_WATER("main/ocean.jpg"),
    BACKGROUND_BEIGE_SMALL("main/ingame_panel_beigeLight_130_75.png"),
    BACKGROUND_BEIGE_SMALLEST("main/ingame_panel_beigeLight_50_150.png"),
    BACKGROUND_300_400("main/panelInset_beigeLight_300_400.png"),
    BACKGROUND_320_420("main/panelInset_beigeLight_420_320.png"),
    GRADIENT_BAR("main/gradient_bar.png"),

    RAFT_LOGO("main/raft_logo.png"),

    PLAYER("main/player.png"),
    SHARK("main/shark.png"),

    RAFT_IMAGE("main/raft_25_25.png", "main/raft_50_50.png"),
    CAMPFIRE_IMAGE("main/campfire.png"),
    WATERPURIFIER_IMAGE("main/waterpurifier_25_19.png"),
    FISHING_NET_IMAGE("main/fishnet_25_25.png", "main/fishnet_50_50.png"),
    SPEAR_IMAGE("main/spear_23-25.png"),

    PLANK("main/plank_25.png", "main/plank_35.png"),
    LEAF("main/leaf_35.png", "main/leaf_50.png"),
    RUBBISH("main/crushed_coke_can.png"),
    BARREL("main/barrel.png"),

    FISH("main/fish.png"),
    POTATO("main/potatoes_small_25.png"),
    GLASS("main/glass_water_icon.png"),
    ;

    private final ImageView large;
    private final ImageView icon;

    GameImages(String iconUrl, String largeUrl) {
        this.large = new ImageView(largeUrl);
        this.icon = new ImageView(iconUrl);
    }

    GameImages(String url) {
        this(url, url);
    }

    public ImageView getLarge() {
        return this.large;
    }

    public ImageView getIcon() {
        return this.icon;

    }

    public String getLargeUrl() {
        return this.large.getImage().getUrl();
    }

    public String getIconUrl() {
        return this.icon.getImage().getUrl();
    }


}
