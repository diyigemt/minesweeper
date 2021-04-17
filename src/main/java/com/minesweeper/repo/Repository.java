package com.minesweeper.repo;

import com.minesweeper.map.Grid;
import com.minesweeper.map.Map;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * create by CarriSun 2021/4/18
 */

public class Repository implements IRepo {
    private static Repository INSTANCE;
    public static final String HISTORY_FILE_NAME = "history.txt";
    public static final String SUCCESS_HISTORY_FILE_NAME = "success_history.txt";
    public static final String MAP_FILE_NAME = "lastMap.txt";

    private static InputStream getFilePath(String fileName) {
        return Repository.class.getResourceAsStream("/resources/" + fileName);
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    @Override
    public boolean saveGameContext(Map gameMap) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(MAP_FILE_NAME));
            stream.writeObject(gameMap.map);
            stream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean readContext(Map gameMap) {
        try {
            ObjectInputStream stream = new ObjectInputStream(getFilePath(MAP_FILE_NAME));
            gameMap.map = (Grid[][]) stream.readObject();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return true: 成功
     * @retrun false：失败
     */
    @Override
    public boolean updateAllHistory() {
        int history = readAllHistory();
        history++;
        StringBuilder builder = new StringBuilder();
        builder.append(history);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(HISTORY_FILE_NAME), StandardCharsets.UTF_8);
            writer.write(builder.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return true: 成功
     * @retrun false：失败
     */
    @Override
    public boolean updateHistory() {
        int history = readSuccessHistory();
        history++;
        StringBuilder builder = new StringBuilder();
        builder.append(history);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(SUCCESS_HISTORY_FILE_NAME), StandardCharsets.UTF_8);
            writer.write(builder.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return 总游戏次数，-1 代表异常
     */
    @Override
    public int readAllHistory() {
        int result = -1;
        try {
            result = Integer.parseInt(new BufferedReader(new FileReader(HISTORY_FILE_NAME)).readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return 胜利次数，-1 代表异常
     */
    @Override
    public int readSuccessHistory() {
        int result = -1;
        try {
            result = Integer.parseInt(new BufferedReader(new InputStreamReader(getFilePath(MAP_FILE_NAME))).readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean clearHistory() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(HISTORY_FILE_NAME), StandardCharsets.UTF_8);
            writer.write("0");
            writer.close();
            OutputStreamWriter successWriter = new OutputStreamWriter(new FileOutputStream(SUCCESS_HISTORY_FILE_NAME), StandardCharsets.UTF_8);
            successWriter.write("0");
            successWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 测试
    public static void main(String[] args) throws Exception {
        System.out.println(getInstance().updateAllHistory());
        System.out.println(getInstance().updateHistory());
        System.out.println(getInstance().readAllHistory());
        System.out.println(getInstance().readSuccessHistory());
        getInstance().clearHistory();
        System.out.println(getInstance().readAllHistory());
        System.out.println(getInstance().readSuccessHistory());
    }
}
