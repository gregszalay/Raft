/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package game;

import components.*;
import data.SaveLoad;
import data.SaveSlot;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import settings.ResourcesRequired;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static data.GameImages.*;
import static settings.GameSettings.*;
import static settings.ResourcesRequired.*;

public class GameView {

    private final AnchorPane gamePane;
    private final Scene gameScene;
    private final Stage gameStage;
    private final List<Integer> reservedIndexList = new ArrayList<>();
    private final Random randomGenerator = new Random();
    private Stage menuStage;
    private GridPane backgroundGridPaneFirst;
    private GridPane backgroundGridPaneSecond;
    private GridPane raftGridPane;
    private double gameHeight;
    private double gameWidth;
    private AnimationTimer gameTimer;
    private int gameLoopCounter;
    private GradientBar hungerSubScene;
    private GradientBar thirstSubScene;
    private GradientBar spearDefenceSubScene;
    private boolean isFishCooking;
    private boolean isPotatoCooking;
    private boolean isGameMaximised;
    private ImageView player;
    private ImageView shark;
    private int previousSharkDirection;
    private ImageView[] raftAndFishingNetArray;
    private ImageView[] campfireArray;
    private ImageView[] waterPurifierArray;
    private ImageView[] spearArray;
    private ImageView[] plankArray;
    private ImageView[] rubbishArray;
    private ImageView[] leafArray;
    private ImageView[] barrelArray;
    private GameLabel raftPanelsBuiltLabel;
    private GameLabel campfiresBuiltLabel;
    private GameLabel waterPurifiersBuiltLabel;
    private GameLabel spearsBuiltLabel;
    private GameLabel fishingNetsBuiltLabel;
    private GameLabel waterCollectedLabel;
    private GameLabel fishCollectedLabel;
    private GameLabel potatoesCollectedLabel;
    private GameLabel planksCollectedLabel;
    private GameLabel leavesCollectedLabel;
    private GameLabel rubbishCollectedLabel;
    private GameLabel hungerLevelLabel;
    private GameLabel thirstLevelLabel;
    private GameLabel spearDefenceLabel;
    private GameLabel timerLabel;
    private GameLabel numberOfPlayerActionsLabel;
    private Label tipsLabel;
    private GameButton raftPanelButton;
    private GameButton campfireButton;
    private GameButton waterPurifierButton;
    private GameButton spearsButton;
    private GameButton fishingNetButton;
    private GameButton cookFishButton;
    private GameButton cookPotatoesButton;
    private GameButtonBox playerLivesBox;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isSpearDefenceActive;
    private int spearDefenceCounter;
    private PauseScene pauseSubScene;
    private VBox pauseBox;
    private int gameLoopIntervalMillis;

    /**
     * The constructor initialises the main layout components of the game and calls the createNewGame() method below.
     */
    public GameView() {
        gameHeight = GAME_HEIGHT_DEFAULT.getDoubleValue();
        gameWidth = GAME_WIDTH_DEFAULT.getDoubleValue();
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, gameWidth, gameHeight);
        gameStage = new Stage();
        isGameMaximised = false;
        createNewGame();
    }

    /**
     * ****************************************************************************************
     * PART (A) - A new game is created.
     * ****************************************************************************************
     */

    /**
     * Creates all components of the the game by calling the methods declared and implemented below.
     */
    private void createNewGame() {
        createBackground();
        createShark();
        createGameElements();
        createRaft();
        createPlayer();
        createLabels();
        createButtons();
        createBoxes();
        createGameLoop();
    }

    /**
     * These methods are responsible for creating a new game. These are called by the createNewGame() method above.
     */
    private void createBackground() {
        ImageView backgroundImage = BACKGROUND_WATER.getLarge();
        backgroundGridPaneFirst = new GridPane();
        backgroundGridPaneSecond = new GridPane();
        backgroundGridPaneFirst.getChildren().add(backgroundImage);
        backgroundGridPaneSecond.getChildren().add(backgroundImage);
        backgroundGridPaneSecond.setLayoutY(-1024);
        gamePane.getChildren().addAll(backgroundGridPaneFirst, backgroundGridPaneSecond);

    }

    private void createShark() {
        shark = new ImageView(SHARK.getLargeUrl());
        shark.setLayoutX(randomGenerator.nextInt((int) gameWidth));
        shark.setLayoutY(randomGenerator.nextInt((int) gameHeight));
        gamePane.getChildren().add(shark);
    }

    private void createRaft() {
        raftGridPane = new GridPane();
        raftGridPane.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(raftGridPane, 0.0);
        AnchorPane.setBottomAnchor(raftGridPane, 0.0);
        AnchorPane.setTopAnchor(raftGridPane, 0.0);
        AnchorPane.setLeftAnchor(raftGridPane, 0.0);
        gamePane.getChildren().add(raftGridPane);

        raftAndFishingNetArray = new ImageView[RAFT_PANELS_MAX_AMOUNT.getIntValue()];
        /*
        Create the first 4 panels:
         */
        raftAndFishingNetArray[0] = new ImageView(RAFT_IMAGE.getLargeUrl());
        addRaftPanelToRaftGrid(0, 0, 0);

        raftAndFishingNetArray[1] = new ImageView(RAFT_IMAGE.getLargeUrl());
        addRaftPanelToRaftGrid(1, 0, 1);

        raftAndFishingNetArray[2] = new ImageView(RAFT_IMAGE.getLargeUrl());
        addRaftPanelToRaftGrid(2, 1, 0);

        raftAndFishingNetArray[3] = new ImageView(RAFT_IMAGE.getLargeUrl());
        addRaftPanelToRaftGrid(3, 1, 1);

    }

    private void addRaftPanelToRaftGrid(int raftPanelIndex, int rowIndex, int columnIndex) {
        GridPane.setRowIndex(raftAndFishingNetArray[raftPanelIndex], rowIndex);
        GridPane.setColumnIndex(raftAndFishingNetArray[raftPanelIndex], columnIndex);
        raftGridPane.getChildren().add(raftAndFishingNetArray[raftPanelIndex]);
    }

    private void createPlayer() {
        player = PLAYER.getLarge();
        movePlayerToCenter();
    }

    private void createGameElements() {
        plankArray = createResourceArray(PLANK.getLargeUrl(), RESOURCES_FREQUENT_AMOUNT.getIntValue());
        leafArray = createResourceArray(LEAF.getLargeUrl(), RESOURCES_FREQUENT_AMOUNT.getIntValue());
        rubbishArray = createResourceArray(RUBBISH.getLargeUrl(), RESOURCES_FREQUENT_AMOUNT.getIntValue());
        barrelArray = createResourceArray(BARREL.getLargeUrl(), RESOURCES_RARE_AMOUNT.getIntValue());
        campfireArray = createUtilityArray(UTILITIES_MAX_AMOUNT.getIntValue());
        waterPurifierArray = createUtilityArray(UTILITIES_MAX_AMOUNT.getIntValue());
        spearArray = createUtilityArray(UTILITIES_MAX_AMOUNT.getIntValue());

    }

    private ImageView[] createResourceArray(String resourceImageUrl, int arraySize) {
        ImageView[] newResourceArray = new ImageView[arraySize];
        for (int i = 0; i < newResourceArray.length; i++) {
            newResourceArray[i] = new ImageView(resourceImageUrl);
            setNewElementsPosition(newResourceArray[i]);
            gamePane.getChildren().add(newResourceArray[i]);
        }
        return newResourceArray;
    }

    private ImageView[] createUtilityArray(int arraySize) {
        return new ImageView[arraySize];
    }

    private void createLabels() {
        raftPanelsBuiltLabel = new GameLabel();
        campfiresBuiltLabel = new GameLabel();
        waterPurifiersBuiltLabel = new GameLabel();
        fishingNetsBuiltLabel = new GameLabel();
        spearsBuiltLabel = new GameLabel();
        fishCollectedLabel = new GameLabel();
        potatoesCollectedLabel = new GameLabel();
        waterCollectedLabel = new GameLabel();
        planksCollectedLabel = new GameLabel();
        leavesCollectedLabel = new GameLabel();
        rubbishCollectedLabel = new GameLabel();
        hungerLevelLabel = new GameLabel("Éhség", 12);
        hungerLevelLabel.increment(100);
        thirstLevelLabel = new GameLabel("Szomj", 12);
        thirstLevelLabel.increment(100);
        spearDefenceLabel = new GameLabel("Lándzsa", 12);
        timerLabel = new GameLabel("Játékidő (mp.)", 12);
        numberOfPlayerActionsLabel = new GameLabel("Cselekvések", 12);

        tipsLabel = new Label();
        Font myfont = new Font("-fx-text-fill: white", 21);
        tipsLabel.setFont(myfont);
        tipsLabel.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(tipsLabel, 20.0);
        AnchorPane.setLeftAnchor(tipsLabel, 20.0);
        AnchorPane.setRightAnchor(tipsLabel, 20.0);
        gamePane.getChildren().add(tipsLabel);

        tipsLabel.setText("Tipp: Használd a > ^ ˇ < billentyűket a mozgáshoz.");

    }

    private void createButtons() {
        raftPanelButton = new GameButton(RAFT_IMAGE.getIcon(), "Tutaj bővítése: 2 deszka + 2 levél");
        campfireButton = new GameButton(CAMPFIRE_IMAGE.getIcon(), "Tűzhely: 2 deszka + 4 levél + 3 hulladék");
        waterPurifierButton = new GameButton(WATERPURIFIER_IMAGE.getIcon(), "Víztisztító: 2 levél + 4 hulladék");
        fishingNetButton = new GameButton(FISHING_NET_IMAGE.getIcon(), "Háló: 2 deszka + 6 levél");
        spearsButton = new GameButton(SPEAR_IMAGE.getIcon(), "Lándzsa készítése: 4 deszka + 4 levél + 4 hulladék");
        cookFishButton = new GameButton(FISH.getIcon(), "Halsütés. Sütés ideje: 25 cselekvés");
        cookPotatoesButton = new GameButton(POTATO.getIcon(), "Krumpli sütés. Sütés ideje: 25 cselekvés");
    }

    private void createBoxes() {
        double margin = 7;

        GameButtonBox raftPanelBox = new GameButtonBox(raftPanelButton, raftPanelsBuiltLabel);
        GameButtonBox campfireBox = new GameButtonBox(campfireButton, campfiresBuiltLabel);
        GameButtonBox waterPurifierBox = new GameButtonBox(waterPurifierButton, waterPurifiersBuiltLabel);
        GameButtonBox fishingNetBox = new GameButtonBox(fishingNetButton, fishingNetsBuiltLabel);
        GameButtonBox spearBox = new GameButtonBox(spearsButton, spearsBuiltLabel);
        GameButtonBox fishBox = new GameButtonBox(cookFishButton, fishCollectedLabel);
        GameButtonBox potatoesBox = new GameButtonBox(cookPotatoesButton, potatoesCollectedLabel);
        GameButtonBox glassOfWaterBox = new GameButtonBox(GLASS.getIcon(), waterCollectedLabel);
        GameButtonBox planksBox = new GameButtonBox(PLANK.getIcon(), planksCollectedLabel);
        GameButtonBox leavesBox = new GameButtonBox(LEAF.getIcon(), leavesCollectedLabel);
        GameButtonBox rubbishBox = new GameButtonBox(RUBBISH.getIcon(), rubbishCollectedLabel);

        VBox utilitiesControlBox = new VBox(raftPanelBox, campfireBox, waterPurifierBox, fishingNetBox, spearBox);
        utilitiesControlBox.setAlignment(Pos.BOTTOM_LEFT);
        utilitiesControlBox.setSpacing(margin);

        VBox foodsControlBox = new VBox(fishBox, potatoesBox, glassOfWaterBox);
        foodsControlBox.setAlignment(Pos.BOTTOM_LEFT);
        foodsControlBox.setSpacing(margin);

        VBox resourcesBox = new VBox(planksBox, leavesBox, rubbishBox);
        resourcesBox.setAlignment(Pos.BOTTOM_LEFT);
        resourcesBox.setSpacing(margin);

        HBox parentBox = new HBox(utilitiesControlBox, foodsControlBox, resourcesBox);
        parentBox.setAlignment(Pos.BOTTOM_LEFT);
        parentBox.setSpacing(margin);
        AnchorPane.setBottomAnchor(parentBox, 20.0);
        gamePane.getChildren().add(parentBox);

        playerLivesBox = new GameButtonBox(new ImageView(PLAYER.getLargeUrl()),
                new ImageView(PLAYER.getLargeUrl()),
                new ImageView(PLAYER.getLargeUrl()));
        playerLivesBox.setSpacing(12);
        AnchorPane.setBottomAnchor(playerLivesBox, 20.0);
        AnchorPane.setRightAnchor(playerLivesBox, 20.0);
        gamePane.getChildren().add(playerLivesBox);

        GameButtonBox timerBox = new GameButtonBox(timerLabel);
        timerBox.setCustomBackground(BACKGROUND_BEIGE_SMALLEST.getLargeUrl(), 150, 50);
        timerBox.setSpacing(12);
        timerBox.setMaxHeight(50);
        timerBox.setMinWidth(150);
        gamePane.getChildren().add(timerBox);

        GameButtonBox actionsNumberBox = new GameButtonBox(numberOfPlayerActionsLabel);
        actionsNumberBox.setCustomBackground(BACKGROUND_BEIGE_SMALLEST.getLargeUrl(), 150, 50);
        actionsNumberBox.setSpacing(12);
        actionsNumberBox.setMaxHeight(50);
        actionsNumberBox.setMinWidth(150);
        gamePane.getChildren().add(actionsNumberBox);

        VBox timerAndActionsBox = new VBox(timerBox, actionsNumberBox);
        timerAndActionsBox.setSpacing(12);
        AnchorPane.setTopAnchor(timerAndActionsBox, 20.0);
        AnchorPane.setLeftAnchor(timerAndActionsBox, 20.0);
        gamePane.getChildren().add(timerAndActionsBox);

        hungerSubScene = new GradientBar();
        hungerSubScene.getPane().getChildren().addAll(hungerLevelLabel);
        gamePane.getChildren().add(hungerSubScene);

        thirstSubScene = new GradientBar();
        thirstSubScene.getPane().getChildren().addAll(thirstLevelLabel);
        gamePane.getChildren().add(thirstSubScene);

        spearDefenceSubScene = new GradientBar();
        spearDefenceSubScene.getPane().getChildren().addAll(spearDefenceLabel);
        spearDefenceSubScene.setToEmpty();
        gamePane.getChildren().add(spearDefenceSubScene);

        HBox hungerBox = new HBox(hungerSubScene);

        HBox thirstBox = new HBox(thirstSubScene);

        HBox spearDefenceBox = new HBox(spearDefenceSubScene);

        VBox hungerThirstSpearBox = new VBox(hungerBox, thirstBox, spearDefenceBox);
        hungerThirstSpearBox.setSpacing(7);
        gamePane.getChildren().add(hungerThirstSpearBox);
        AnchorPane.setBottomAnchor(hungerThirstSpearBox, 20.0);
        AnchorPane.setRightAnchor(hungerThirstSpearBox, 20.0);
        AnchorPane.setTopAnchor(hungerThirstSpearBox, 20.0);


    }

    /**
     * This method is the most important to the game logic. It is responsible for calling all methods needed in every
     * game loop during the gameplay.
     */
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoopCounter++;
                callCheckMethods();
                if (gameLoopCounter % GAME_LOOPS_PER_SECOND.getIntValue() == 0) {
                    /*
                    Update game timer:
                    */
                    timerLabel.increment();
                }
                if (gameLoopCounter % GAME_LOOPS_PER_ACTION.getIntValue() == 0) {
                    /*
                    Update number of player actions and call methods that should only be called on each player action:
                    */
                    numberOfPlayerActionsLabel.increment();
                    callMethodsOnlyForEachAction();
                }
                callMoveMethods();
            }

        };
    }

    /**
     * ****************************************************************************************
     * PART (B) - Independent gameplay logic.
     * ****************************************************************************************
     * These methods are called on each game loop. These can be viewed as helper methods of the createGameLoop() method.
     */

    private void callCheckMethods() {
        checkWindowSize();
        checkPositionAndRelocateAllResources();
        collectResources();
        if (gameLoopCounter == Integer.MAX_VALUE) {
            gameLoopCounter = 0;
        }
    }

    private void callMoveMethods() {
        movePlayer();
        moveBackground();
        moveAllResources();
        moveShark();
    }

    private void callMethodsOnlyForEachAction() {
        if (numberOfPlayerActionsLabel.getValue() == END_OF_GAME_ACTION_NUMBER.getIntValue()) {
            gameTimer.stop();
            endOfGameAlert();
            gameTimer.start();
        }
        drink();
        fish();
        increaseHungerAndThirst();
        if (gameLoopCounter % 25 == 0) {
            makeWater();
            if (isFishCooking) {
                cookFish();
            }
            if (isPotatoCooking) {
                cookPotatoes();
            }
        }
        if (isSpearDefenceActive) {
            spearDefenceCounter++;
            spearDefenceSubScene.decrement(100 / ACTIONS_PER_SPEAR_DEFENCE.getIntValue());
            spearDefenceLabel.decrement();
            if (spearDefenceCounter >= ACTIONS_PER_SPEAR_DEFENCE.getIntValue()) {
                isSpearDefenceActive = false;
                spearDefenceSubScene.setToEmpty();
                spearDefenceLabel.setTextValue(0);
            }
        }
    }

    private void checkWindowSize() {
        if (isGameMaximised != gameStage.isMaximized()) {
            gameHeight = gameStage.getHeight();
            gameWidth = gameStage.getWidth();
            movePlayerToCenter();
            isGameMaximised = gameStage.isMaximized();
        }
    }

    private void checkPositionAndRelocateAllResources() {
        checkPositionAndRelocateResource(plankArray);
        checkPositionAndRelocateResource(rubbishArray);
        checkPositionAndRelocateResource(leafArray);
        checkPositionAndRelocateResource(barrelArray);
    }

    private void checkPositionAndRelocateResource(ImageView[] resourceArray) {
        for (ImageView imageView : resourceArray) {
            if (imageView.getLayoutY() > gameHeight) {
                setNewElementsPosition(imageView);
            }
        }
    }

    private void collectResources() {
        if (isPlayerNearResource(plankArray)) {
            planksCollectedLabel.increment();
        }
        if (isPlayerNearResource(rubbishArray)) {
            rubbishCollectedLabel.increment();
        }
        if (isPlayerNearResource(leafArray)) {
            leavesCollectedLabel.increment();
        }
        if (isPlayerNearResource(barrelArray)) {
            for (int j = 0; j < 5; j++) {
                switch (randomGenerator.nextInt(4)) {
                    case 0:
                        planksCollectedLabel.increment();
                        break;
                    case 1:
                        leavesCollectedLabel.increment();
                        break;
                    case 2:
                        rubbishCollectedLabel.increment();
                        break;
                    case 3:
                        potatoesCollectedLabel.increment();
                        break;
                }

            }
        }

    }

    private void moveBackground() {
        backgroundGridPaneFirst.setLayoutY(backgroundGridPaneFirst.getLayoutY() + 0.5);
        backgroundGridPaneSecond.setLayoutY(backgroundGridPaneSecond.getLayoutY() + 0.5);
        if (backgroundGridPaneFirst.getLayoutY() >= 1024) {
            backgroundGridPaneFirst.setLayoutY(-1024);
        }
        if (backgroundGridPaneSecond.getLayoutY() >= 0) {
            backgroundGridPaneSecond.setLayoutY(-1024);
        }
    }

    private void movePlayer() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (player.getLayoutX() > -20) {
                player.setLayoutX(player.getLayoutX() - PLAYER_SPEED.getIntValue());
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (player.getLayoutX() < gameWidth) {
                player.setLayoutX(player.getLayoutX() + PLAYER_SPEED.getIntValue());
            }
        }
        if (isUpKeyPressed && !isDownKeyPressed) {
            if (player.getLayoutY() > -20) {
                player.setLayoutY(player.getLayoutY() - PLAYER_SPEED.getIntValue());
            }
        }
        if (!isUpKeyPressed && isDownKeyPressed) {
            if (player.getLayoutY() < gameHeight) {
                player.setLayoutY(player.getLayoutY() + PLAYER_SPEED.getIntValue());
            }
        }

    }

    private void moveAllResources() {
        moveResource(plankArray);
        moveResource(rubbishArray);
        moveResource(leafArray);
        moveResource(barrelArray);
    }

    private void moveResource(ImageView[] resourceArray) {
        for (ImageView imageView : resourceArray) {
            imageView.setLayoutY(imageView.getLayoutY() + RESOURCE_SPEED.getDoubleValue());
            imageView.setRotate(imageView.getRotate() + RESOURCE_SPEED.getDoubleValue());
        }
    }

    private void moveShark() {
        double playerSharkDistance = calculateDistance(player.getLayoutX(), shark.getLayoutX(),
                player.getLayoutY(), shark.getLayoutY());
        if (isPlayerOnRaft()) {
            moveSharkCalmly(SHARK_SPEED_NORMAL.getDoubleValue());
        } else {
            moveSharkAgressively(SHARK_SPEED_FAST.getDoubleValue());
        }

        if (playerSharkDistance < SHARK_RADIUS.getDoubleValue() + PLAYER_RADIUS.getDoubleValue()
                && !isPlayerOnRaft()
                && !isSpearDefenceActive) {
            removeLife();
            tipsLabel.setText("Elkapott a cápa!");
            setNewElementsPosition(shark);
        }
        if (shark.getLayoutX() > gameWidth || shark.getLayoutY() > gameHeight) {
            setNewElementsPosition(shark);
        }

    }

    private void moveSharkCalmly(double dydxNormal) {
        int direction;
        double distanceRandom = randomGenerator.nextDouble();
        if (randomGenerator.nextInt(50) == 0) {
            direction = randomGenerator.nextInt(9);
        } else {
            direction = previousSharkDirection;
        }
        switch (direction) {
            case 0:
                break;
            case 1:
                shark.setLayoutX(shark.getLayoutX() + (dydxNormal + distanceRandom));
                previousSharkDirection = 1;
                break;
            case 2:
                shark.setLayoutX(shark.getLayoutX() - (dydxNormal + distanceRandom));
                previousSharkDirection = 2;
                break;
            case 3:
                shark.setLayoutY(shark.getLayoutY() + (dydxNormal + distanceRandom));
                previousSharkDirection = 3;
                break;
            case 4:
                shark.setLayoutY(shark.getLayoutY() - (dydxNormal + distanceRandom));
                previousSharkDirection = 4;
                break;
            case 5:
                shark.setLayoutY(shark.getLayoutY() + (dydxNormal + distanceRandom));
                shark.setLayoutX(shark.getLayoutX() + (dydxNormal + distanceRandom));
                previousSharkDirection = 5;
                break;
            case 6:
                shark.setLayoutY(shark.getLayoutY() - (dydxNormal + distanceRandom));
                shark.setLayoutX(shark.getLayoutX() + (dydxNormal + distanceRandom));
                previousSharkDirection = 6;
                break;
            case 7:
                shark.setLayoutY(shark.getLayoutY() - (dydxNormal + distanceRandom));
                shark.setLayoutX(shark.getLayoutX() + (dydxNormal + distanceRandom));
                previousSharkDirection = 7;
                break;
            case 8:
                shark.setLayoutY(shark.getLayoutY() - (dydxNormal + distanceRandom));
                shark.setLayoutX(shark.getLayoutX() - (dydxNormal + distanceRandom));
                previousSharkDirection = 8;
                break;

        }

    }

    private void moveSharkAgressively(double dydxFast) {
        if (player.getLayoutX() < shark.getLayoutX()) {
            shark.setLayoutX(shark.getLayoutX() - dydxFast);
        } else {
            shark.setLayoutX(shark.getLayoutX() + dydxFast);
        }

        if (player.getLayoutY() < shark.getLayoutY()) {
            shark.setLayoutY(shark.getLayoutY() - dydxFast);
        } else {
            shark.setLayoutY(shark.getLayoutY() + dydxFast);
        }

    }

    private void drink() {
        if (isPlayerNearUtility(waterPurifierArray) && waterCollectedLabel.getValue() >= 1) {
            waterCollectedLabel.decrement();
            if (thirstLevelLabel.getValue() >= 60) {
                thirstLevelLabel.setTextValue(100);
                thirstSubScene.setToFull();
            } else {
                thirstLevelLabel.increment(40);
                thirstSubScene.increment(40);
            }
        }
    }

    private void fish() {
        if (!isPlayerOnRaft()) {
            switch (randomGenerator.nextInt(4)) {
                case 0:
                    fishCollectedLabel.increment();
                    break;
                case 1 | 2 | 3:
                    rubbishCollectedLabel.increment();
                    break;
            }
        }
    }

    private void makeWater() {
        waterCollectedLabel.increment(waterPurifiersBuiltLabel.getValue() * 25);
    }

    private void increaseHungerAndThirst() {
        hungerLevelLabel.decrement();
        hungerSubScene.decrement();
        if (hungerLevelLabel.getValue() == 0) {
            removeLife();
            tipsLabel.setText("Éhenhaltál!");
            hungerLevelLabel.setTextValue(100);
            hungerSubScene.setToFull();
        }

        thirstLevelLabel.decrement();
        thirstSubScene.decrement();
        if (thirstLevelLabel.getValue() == 0) {
            removeLife();
            tipsLabel.setText("Szomjanhaltál!");
            thirstLevelLabel.setTextValue(100);
            thirstSubScene.setToFull();
        }
    }

    private void cookFish() {
        if (fishCollectedLabel.getValue() >= 1 && campfiresBuiltLabel.getValue() >= 1) {
            fishCollectedLabel.decrement();
            if (hungerLevelLabel.getValue() >= 40) {
                hungerLevelLabel.setTextValue(100);
                hungerSubScene.setToFull();
            } else {
                hungerLevelLabel.increment(40);
                hungerSubScene.increment(40);
            }
        }
        isFishCooking = false;
    }

    private void cookPotatoes() {
        if (potatoesCollectedLabel.getValue() >= 1 && campfiresBuiltLabel.getValue() >= 1) {
            if (hungerLevelLabel.getValue() <= 75) {
                potatoesCollectedLabel.decrement();
                hungerLevelLabel.increment(25);
                hungerSubScene.increment(25);

            } else {
                potatoesCollectedLabel.decrement();
                int step = 100 - hungerLevelLabel.getValue();
                hungerLevelLabel.increment(step);
                hungerSubScene.increment(step);

            }

        }
        isPotatoCooking = false;
    }

    private void removeLife() {
        removeLife(1);
    }

    private void removeLife(int howMany) {
        for (int i = 0; i < howMany; i++) {

            if (playerLivesBox.getChildren().size() <= 1) {
                gameStage.close();
                gameTimer.stop();
                menuStage.show();
            } else {
                playerLivesBox.getChildren().remove(playerLivesBox.getChildren().size() - 1);
            }
        }
        movePlayerToCenter();
    }

    private void movePlayerToCenter() {
        player.setLayoutX(gameWidth / 2);
        player.setLayoutY(gameHeight / 2);
        refreshPlayer();
    }

    private Boolean isPlayerNearUtility(ImageView[] utilityArray) {
        for (ImageView imageView : utilityArray) {
            if (imageView != null
                    && PLAYER_RADIUS.getDoubleValue() + RESOURCE_RADIUS.getDoubleValue() >
                    calculateDistance(player.getLayoutX() + 49, imageView.getLayoutX() + 20,
                            player.getLayoutY() + 37, imageView.getLayoutY() + 20)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isPlayerNearResource(ImageView[] resourceArray) {
        for (ImageView imageView : resourceArray) {
            if (imageView != null
                    && PLAYER_RADIUS.getDoubleValue() + RESOURCE_RADIUS.getDoubleValue() >
                    calculateDistance(player.getLayoutX() + 49, imageView.getLayoutX() + 20,
                            player.getLayoutY() + 37, imageView.getLayoutY() + 20)) {
                setNewElementsPosition(imageView);
                return true;
            }
        }
        return false;
    }

    private boolean isPlayerOnRaft() {
        for (ImageView raftImageView : raftAndFishingNetArray) {
            if (raftImageView != null) {
                double raftTopY = raftImageView.getLayoutY();
                double raftBottomY = raftImageView.getLayoutY() + raftImageView.getImage().getHeight();
                double raftLeftX = raftImageView.getLayoutX();
                double raftRightX = raftImageView.getLayoutX() + raftImageView.getImage().getWidth();
                double playerX = player.getLayoutX();
                double playerY = player.getLayoutY();
                if (calculateDistance(playerX, raftLeftX, playerY, raftTopY) < PLAYER_RADIUS.getDoubleValue() + RAFT_RADIUS.getDoubleValue()
                        || calculateDistance(playerX, raftLeftX, playerY, raftBottomY) < PLAYER_RADIUS.getDoubleValue() + RAFT_RADIUS.getDoubleValue()
                        || calculateDistance(playerX, raftRightX, playerY, raftTopY) < PLAYER_RADIUS.getDoubleValue() + RAFT_RADIUS.getDoubleValue()
                        || calculateDistance(playerX, raftRightX, playerY, raftBottomY) < PLAYER_RADIUS.getDoubleValue() + RAFT_RADIUS.getDoubleValue()
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    private void setNewElementsPosition(ImageView imageView) {
        imageView.setLayoutX(randomGenerator.nextInt((int) gameWidth));
        imageView.setLayoutY(-(randomGenerator.nextInt((int) gameHeight + 20)));
    }

    private void refreshPlayer() {
        gamePane.getChildren().remove(player);
        gamePane.getChildren().add(player);
    }

    private void endOfGameAlert() {
        CustomAlert endOfGameAlert = new CustomAlert(Alert.AlertType.INFORMATION);
        endOfGameAlert.setTitle("Játég vége");
        endOfGameAlert.setHeaderText("Játék vége!");
        endOfGameAlert.setContentText("Elérted az 1500 cselekvést. Tulajdonsképpen megnyerted a játékot, de " +
                "nyugodtan játszhats tovább is!");
        endOfGameAlert.show();
    }

    /**
     * ****************************************************************************************
     * PART (C) - User-controlled gameplay logic.
     * ****************************************************************************************
     * These methods are called only when the user interacts with the game.
     */

    /**
     * Public methods called by the controller.
     */
    public void spearDefence() {
        if (spearsBuiltLabel.getValue() >= 1) {
            spearsBuiltLabel.decrement();
            isSpearDefenceActive = true;
            spearDefenceSubScene.setToFull();
            spearDefenceLabel.setTextValue(5);
        }
    }

    public void endSpearDefence() {
        isSpearDefenceActive = false;
        spearDefenceSubScene.setToEmpty();
        spearDefenceLabel.setTextValue(0);
    }

    public void build(ResourcesRequired utility) {
        if (planksCollectedLabel.getValue() >= utility.getPlanksNeeded()
                && leavesCollectedLabel.getValue() >= utility.getLeavesNeeded()
                && rubbishCollectedLabel.getValue() >= utility.getRubbishNeeded()) {
            if (utility == RAFT_PANEL) {
                addRaftPanel();
            } else if (utility == SPEAR) {
                addSpear();
            } else if (utility == CAMPFIRE) {
                addCampfire();
            } else if (utility == WATERPURIFIER) {
                addWaterPurifier();
            } else if (utility == FISHING_NET) {
                addFishingNet();
            }
            planksCollectedLabel.decrement(utility.getPlanksNeeded());
            leavesCollectedLabel.decrement(utility.getLeavesNeeded());
            rubbishCollectedLabel.decrement(utility.getRubbishNeeded());
            refreshPlayer();
        }
    }

    /**
     * Private helper methods of the public methods above.
     */
    private void addRaftPanel() {
        addRaftPanel(1);

    }

    private void addRaftPanel(int howMany) {
        for (int i = 0; i < howMany; i++) {
            for (int j = 1; j < raftAndFishingNetArray.length; j++) {
                if (raftAndFishingNetArray[j] == null) {
                    raftAndFishingNetArray[j] = new ImageView(RAFT_IMAGE.getLargeUrl());
                    placeRaftPanel(raftAndFishingNetArray, j);
                    raftPanelsBuiltLabel.increment();
                    break;
                }
            }
        }

    }

    private void addFishingNet() {
        addFishingNet(1);

    }

    private void addFishingNet(int howMany) {
        for (int i = 0; i < howMany; i++) {
            for (int j = 1; j < raftAndFishingNetArray.length; j++) {
                if (raftAndFishingNetArray[j] == null) {
                    raftAndFishingNetArray[j] = new ImageView(FISHING_NET_IMAGE.getLargeUrl());
                    placeRaftPanel(raftAndFishingNetArray, j);
                    fishingNetsBuiltLabel.increment();
                    break;
                }
            }
        }
    }

    private void addCampfire() {
        addCampfire(1);

    }

    private void addCampfire(int howMany) {
        for (int i = 0; i < howMany; i++) {
            int newIndex = createUtility(campfireArray);
            placeUtility(campfireArray[newIndex]);
            campfiresBuiltLabel.increment();
        }

    }

    private void addWaterPurifier() {
        addWaterPurifier(1);
    }

    private void addWaterPurifier(int howMany) {
        for (int i = 0; i < howMany; i++) {
            int newIndex = createUtility(waterPurifierArray);
            placeUtility(waterPurifierArray[newIndex]);
            waterPurifiersBuiltLabel.increment();
        }

    }

    private void addSpear() {
        addSpear(1);

    }

    private void addSpear(int howMany) {
        for (int i = 0; i < howMany; i++) {
            int newIndex = createUtility(spearArray);
            placeUtility(spearArray[newIndex]);
            spearsBuiltLabel.increment();
        }

    }

    private int createUtility(ImageView[] elementImageArray) {
        for (int i = 0; i < elementImageArray.length; i++) {
            if (elementImageArray[i] == null) {
                if (elementImageArray == campfireArray) {
                    elementImageArray[i] = new ImageView(CAMPFIRE_IMAGE.getLargeUrl());
                } else if (elementImageArray == waterPurifierArray) {
                    elementImageArray[i] = new ImageView(WATERPURIFIER_IMAGE.getLargeUrl());
                } else if (elementImageArray == spearArray) {
                    elementImageArray[i] = new ImageView(SPEAR_IMAGE.getLargeUrl());
                }
                return i;
            }
        }
        return -1;
    }

    private void placeUtility(ImageView utilityImage) {
        for (int raftIndex = raftAndFishingNetArray.length - 1; 0 <= raftIndex; raftIndex--) {
            if (raftAndFishingNetArray[raftIndex] != null
                    && !raftAndFishingNetArray[raftIndex].getImage().getUrl().contains(FISHING_NET_IMAGE.getLargeUrl())
                    && !reservedIndexList.contains(raftIndex)) {
                GridPane.setRowIndex(utilityImage, GridPane.getRowIndex(raftAndFishingNetArray[raftIndex]));
                GridPane.setColumnIndex(utilityImage, GridPane.getColumnIndex(raftAndFishingNetArray[raftIndex]));
                raftGridPane.getChildren().add(utilityImage);
                reservedIndexList.add(raftIndex);
                break;
            }

        }

    }

    private void placeRaftPanel(ImageView[] raftImageArray, int raftIndex) {
        boolean isPlaced = false;
        int previousPanelRowIndex = GridPane.getRowIndex(raftImageArray[raftIndex - 1]);
        int previousPanelColumnIndex = GridPane.getColumnIndex(raftImageArray[raftIndex - 1]);
        while (!isPlaced) {
            if (previousPanelColumnIndex >= 0 && previousPanelRowIndex >= 0) {
                switch (randomGenerator.nextInt(4)) {
                    /*place to the right of previous*/
                    case 0:
                        GridPane.setColumnIndex(raftImageArray[raftIndex], previousPanelColumnIndex + 1);
                        GridPane.setRowIndex(raftImageArray[raftIndex], previousPanelRowIndex);
                        isPlaced = true;
                        break;
                    /*place below previous*/
                    case 1:
                        GridPane.setColumnIndex(raftImageArray[raftIndex], previousPanelColumnIndex);
                        GridPane.setRowIndex(raftImageArray[raftIndex], previousPanelRowIndex + 1);
                        isPlaced = true;
                        break;
                    /*place to the left of previous*/
                    case 2:
                        GridPane.setColumnIndex(raftImageArray[raftIndex], previousPanelColumnIndex - 1);
                        GridPane.setRowIndex(raftImageArray[raftIndex], previousPanelRowIndex);
                        isPlaced = true;
                        break;
                    /*place above previous*/
                    case 3:
                        GridPane.setColumnIndex(raftImageArray[raftIndex], previousPanelColumnIndex);
                        GridPane.setRowIndex(raftImageArray[raftIndex], previousPanelRowIndex - 1);
                        isPlaced = true;
                        break;
                }
            }
            /* Check if placement is valid*/
            if (raftImageArray[raftIndex].getLayoutX() < 0
                    || raftImageArray[raftIndex].getLayoutX() > gameWidth
                    || raftImageArray[raftIndex].getLayoutY() < 0
                    || raftImageArray[raftIndex].getLayoutY() > gameHeight) {
                isPlaced = false;
            } else {
                for (ImageView raftImageView : raftImageArray) {
                    if (raftImageView != null
                            && raftImageView != raftImageArray[raftIndex]
                            && GridPane.getRowIndex(raftImageView).equals(GridPane.getRowIndex(raftImageArray[raftIndex]))
                            && GridPane.getColumnIndex(raftImageView).equals(GridPane.getColumnIndex(raftImageArray[raftIndex]))
                    ) {
                        isPlaced = false;
                        break;
                    }
                }
            }
        }
        raftGridPane.getChildren().add(raftImageArray[raftIndex]);
        raftGridPane.setAlignment(Pos.CENTER);

    }

    /**
     * ****************************************************************************************
     * PART (D) - Start, pause, continue gameplay.
     * ****************************************************************************************
     * These public methods are responsible starting, pausing and continuing the gameplay.
     * These are called by the controllers.
     */

    public void startGame(Stage menuStage) {
        gameStage.setScene(gameScene);
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
        gameTimer.start();
        pauseSubScene = new PauseScene(this, this.menuStage, gameStage, gameTimer);
    }

    public void pauseGame() {
        gameTimer.stop();
        pauseBox = new VBox(pauseSubScene);
        pauseBox.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(pauseBox, 0.0);
        AnchorPane.setBottomAnchor(pauseBox, 0.0);
        AnchorPane.setTopAnchor(pauseBox, 0.0);
        AnchorPane.setLeftAnchor(pauseBox, 0.0);
        gamePane.getChildren().add(pauseBox);
    }

    public void continueGame() {
        gameTimer.start();
        pauseBox.getChildren().remove(pauseSubScene);
        gamePane.getChildren().remove(pauseBox);
        pauseSubScene = new PauseScene(this, this.menuStage, gameStage, gameTimer);
        pauseBox = new VBox(pauseSubScene);
    }

    /**
     * ****************************************************************************************
     * PART (E) - Saving and loading the game.
     * ****************************************************************************************
     */

    public SaveSlot createSaveSlot() {
        return new SaveSlot(gameLoopCounter, gameHeight, gameWidth, isFishCooking, isPotatoCooking, isGameMaximised,
                raftPanelsBuiltLabel.getValue(), campfiresBuiltLabel.getValue(), waterPurifiersBuiltLabel.getValue(),
                spearsBuiltLabel.getValue(), fishingNetsBuiltLabel.getValue(), waterCollectedLabel.getValue(),
                fishCollectedLabel.getValue(), potatoesCollectedLabel.getValue(), planksCollectedLabel.getValue(),
                leavesCollectedLabel.getValue(), rubbishCollectedLabel.getValue(), hungerLevelLabel.getValue(),
                thirstLevelLabel.getValue(), timerLabel.getValue(), playerLivesBox.getChildren().size());
    }

    public void loadGame() throws FileNotFoundException {
        SaveLoad.INSTANCE.loadGame();
        try {
            SaveSlot savedGame = SaveLoad.INSTANCE.getSavedData();
            gameLoopCounter = savedGame.getGameLoopCounter();
            gameHeight = SaveLoad.INSTANCE.getSavedData().getGameHeight();
            gameWidth = savedGame.getGameWidth();
            isFishCooking = savedGame.isFishCooking();
            isPotatoCooking = savedGame.isPotatoCooking();
            isGameMaximised = savedGame.isGameMaximised();

            addRaftPanel(savedGame.getRaftPanelsBuilt());
            addCampfire(savedGame.getRaftPanelsBuilt());
            addWaterPurifier(savedGame.getWaterPurifiersBuilt());
            addSpear(savedGame.getSpearsBuilt());
            addFishingNet(savedGame.getFishingNetsBuilt());

            waterCollectedLabel.increment(savedGame.getWaterCollected());
            fishCollectedLabel.increment(savedGame.getFishCollected());
            potatoesCollectedLabel.increment(savedGame.getPotatoesCollected());
            planksCollectedLabel.increment(savedGame.getPlanksCollected());
            leavesCollectedLabel.increment(savedGame.getLeavesCollected());
            rubbishCollectedLabel.increment(savedGame.getRubbishCollected());

            hungerLevelLabel.decrement(100); //to compensate for 100 value increment in createLabel method
            hungerLevelLabel.increment(savedGame.getHungerLevel());
            thirstLevelLabel.decrement(100); //to compensate for 100 value increment in createLabel method
            thirstLevelLabel.increment(savedGame.getThirstLevel());

            timerLabel.setTextValue(savedGame.getTimer());
            numberOfPlayerActionsLabel.setTextValue(savedGame.getTimer() / 2);

            removeLife(3 - savedGame.getPlayerLives());

        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        }


    }

    /**
     * ****************************************************************************************
     * PART (F) - Getters and Setters.
     * ****************************************************************************************
     */

    public Scene getGameScene() {
        return gameScene;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public GameButton getRaftPanelButton() {
        return raftPanelButton;
    }

    public GameButton getCampfireButton() {
        return campfireButton;
    }

    public GameButton getWaterPurifierButton() {
        return waterPurifierButton;
    }

    public GameButton getSpearsButton() {
        return spearsButton;
    }

    public GameButton getFishingNetButton() {
        return fishingNetButton;
    }

    public GameButton getCookFishButton() {
        return cookFishButton;
    }

    public GameButton getCookPotatoesButton() {
        return cookPotatoesButton;
    }

    public void setFishCooking(boolean fishCooking) {
        isFishCooking = fishCooking;
    }

    public void setPotatoCooking(boolean potatoCooking) {
        isPotatoCooking = potatoCooking;
    }

    public PauseScene getPauseSubScene() {
        return pauseSubScene;
    }

    public void setLeftKeyPressed(boolean leftKeyPressed) {
        isLeftKeyPressed = leftKeyPressed;
    }

    public void setRightKeyPressed(boolean rightKeyPressed) {
        isRightKeyPressed = rightKeyPressed;
    }

    public void setUpKeyPressed(boolean upKeyPressed) {
        isUpKeyPressed = upKeyPressed;
    }

    public void setDownKeyPressed(boolean downKeyPressed) {
        isDownKeyPressed = downKeyPressed;
    }


}
