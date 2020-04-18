/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package data;

import java.io.Serializable;

public class SaveSlot implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int gameLoopCounter;
    private final double gameHeight;
    private final double gameWidth;
    private final boolean isFishCooking;
    private final boolean isPotatoCooking;
    private final boolean isGameMaximised;
    private final int raftPanelsBuilt;
    private final int campfiresBuilt;
    private final int waterPurifiersBuilt;
    private final int spearsBuilt;
    private final int fishingNetsBuilt;
    private final int waterCollected;
    private final int fishCollected;
    private final int potatoesCollected;
    private final int planksCollected;
    private final int leavesCollected;
    private final int rubbishCollected;
    private final int hungerLevel;
    private final int thirstLevel;
    private final int timer;
    private final int playerLives;
    private int actionsNumber;


    public SaveSlot(int gameLoopCounter, double gameHeight, double gameWidth, boolean isFishCooking, boolean isPotatoCooking, boolean isGameMaximised, int raftPanelsBuilt, int campfiresBuilt, int waterPurifiersBuilt, int spearsBuilt, int fishingNetsBuilt, int waterCollected, int fishCollected, int potatoesCollected, int planksCollected, int leavesCollected, int rubbishCollected, int hungerLevel, int thirstLevel, int timer, int playerLives) {
        this.gameLoopCounter = gameLoopCounter;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.isFishCooking = isFishCooking;
        this.isPotatoCooking = isPotatoCooking;
        this.isGameMaximised = isGameMaximised;
        this.raftPanelsBuilt = raftPanelsBuilt;
        this.campfiresBuilt = campfiresBuilt;
        this.waterPurifiersBuilt = waterPurifiersBuilt;
        this.spearsBuilt = spearsBuilt;
        this.fishingNetsBuilt = fishingNetsBuilt;
        this.waterCollected = waterCollected;
        this.fishCollected = fishCollected;
        this.potatoesCollected = potatoesCollected;
        this.planksCollected = planksCollected;
        this.leavesCollected = leavesCollected;
        this.rubbishCollected = rubbishCollected;
        this.hungerLevel = hungerLevel;
        this.thirstLevel = thirstLevel;
        this.timer = timer;
        this.playerLives = playerLives;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getGameLoopCounter() {
        return gameLoopCounter;
    }

    public double getGameHeight() {
        return gameHeight;
    }

    public double getGameWidth() {
        return gameWidth;
    }

    public boolean isFishCooking() {
        return isFishCooking;
    }

    public boolean isPotatoCooking() {
        return isPotatoCooking;
    }

    public boolean isGameMaximised() {
        return isGameMaximised;
    }

    public int getRaftPanelsBuilt() {
        return raftPanelsBuilt;
    }

    public int getCampfiresBuilt() {
        return campfiresBuilt;
    }

    public int getWaterPurifiersBuilt() {
        return waterPurifiersBuilt;
    }

    public int getSpearsBuilt() {
        return spearsBuilt;
    }

    public int getFishingNetsBuilt() {
        return fishingNetsBuilt;
    }

    public int getWaterCollected() {
        return waterCollected;
    }

    public int getFishCollected() {
        return fishCollected;
    }

    public int getPotatoesCollected() {
        return potatoesCollected;
    }

    public int getPlanksCollected() {
        return planksCollected;
    }

    public int getLeavesCollected() {
        return leavesCollected;
    }

    public int getRubbishCollected() {
        return rubbishCollected;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public int getTimer() {
        return timer;
    }

    public int getPlayerLives() {
        return playerLives;
    }
}
