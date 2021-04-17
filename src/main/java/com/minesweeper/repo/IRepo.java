package com.minesweeper.repo;

import com.minesweeper.map.Map;

public interface IRepo {
    public boolean readContext(Map gameMap);
    public boolean saveGameContext(Map gameMap);
    public boolean updateAllHistory();
    public boolean updateHistory();
    public int readAllHistory();
    public int readSuccessHistory();
    public boolean clearHistory();
}
