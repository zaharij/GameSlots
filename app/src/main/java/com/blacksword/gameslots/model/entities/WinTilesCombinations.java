package com.blacksword.gameslots.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class WinTilesCombinations {
    private ArrayList<Map<String, Integer>> winCombinationList = new ArrayList<>();

    /**
     * sets new win map to the list
     * @param newWinLine
     */
    public void setWinLine(HashMap newWinLine){
        winCombinationList.add(newWinLine);
    }

    /**
     * returns win map by its index
     * @param index
     * @return
     */
    public Map<String, Integer> getWinTilesMap(int index){
        if (winCombinationList.size() > index && index >= 0){
            return winCombinationList.get(index);
        }else {
            return null;
        }
    }

    /**
     * returns the index of the last element (map)
     * @return
     */
    public int getLastMapIndex(){
        return winCombinationList.size() - 1;
    }
}
