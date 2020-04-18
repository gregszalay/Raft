/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package data;

import game.GameView;
import javafx.concurrent.Task;

import java.io.*;

public enum SaveLoad {

    INSTANCE; //To call the save function SaveAndLoadUtility.INSTANCE.createSaveSlot();

    private final String DIRECTORY_NAME = "Game Save Data" + File.separator;

    /**
     * Directory where the game files are located!
     */
    private final String PATH_ROOT = "." + File.separator + DIRECTORY_NAME;

    /**
     * Desktop !
     */
    @SuppressWarnings("unused")
    private final String PATH_ROOT_ALT = System.getProperty("user.home") + "/Desktop" + File.separator + DIRECTORY_NAME;

    private final String SAVE_FILE = "gameData.saveSlot";
    private final String IMAGE_TYPE = "png";

    private final String SAVE_FILE_PATH = PATH_ROOT + SAVE_FILE;

    private SaveSlot savedData;


    /**
     * Can be called in order to create a save game
     */
    public void createSaveSlot(GameView gameView) {
        savedData = gameView.createSaveSlot();
        saveGame(savedData);

    }

    /**
     * Method which when called will attempt to save a SaveSlot
     * to a specified directory.
     *
     * @param saveData
     */
    private void saveGame(SaveSlot saveData) {
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                File root = new File(PATH_ROOT);
                File file = new File(SAVE_FILE_PATH);
                file.delete();
                logState("Saving file...");
                try {
                    root.mkdirs();
                    FileOutputStream fileOut = new FileOutputStream(SAVE_FILE_PATH);
                    BufferedOutputStream bufferedStream = new BufferedOutputStream(fileOut);
                    ObjectOutputStream outputStream = new ObjectOutputStream(bufferedStream);
                    outputStream.writeObject(saveData);
                    outputStream.close();
                    fileOut.close();
                    logState("File saved.");
                } catch (IOException e) {
                    logState("Failed to save, I/O exception");
                    e.printStackTrace();
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Method which when called attempts to retrieve the saved data from a specified directory
     */

    public void loadGame() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                File root = new File(PATH_ROOT);
                File file = new File(SAVE_FILE_PATH);
                if (root.exists() && file.exists()) {
                    try {
                        logState("Loading file");
                        FileInputStream fileIn = new FileInputStream(SAVE_FILE_PATH);
                        ObjectInputStream inputStream = new ObjectInputStream(fileIn);
                        savedData = (SaveSlot) inputStream.readObject();
                        inputStream.close();
                        fileIn.close();
                        logState("File loaded");
                    } catch (IOException | ClassNotFoundException e) {
                        logState("Failed to load! " + e.getLocalizedMessage());
                    }
                } else {
                    logState("Nothing to load.");
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    private String logState(String log) {
        System.out.println("Game Saver: " + log);
        return log;
    }

    public SaveSlot getSavedData() {
        return savedData;
    }
}
