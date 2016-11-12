package com.blacksword.gameslots.model.entities;

import java.util.ArrayList;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class Tiles {
    private ArrayList<ArrayList<Tile>> currentTilesArray = new ArrayList<>();

    /**
     * sets one slot to the tile list
     * @param tileArray
     */
    public void setTileArrayToList(ArrayList tileArray){
        this.currentTilesArray.add(tileArray);
    }

    /**
     * returns the tile by its indexes
     * @param tileListIndex
     * @param tileIndex
     * @return
     */
    public Tile getTile(int tileListIndex, int tileIndex){
        if (this.currentTilesArray.size() > 0 && tileListIndex < this.currentTilesArray.size()){
            return this.currentTilesArray.get(tileListIndex).get(tileIndex);
        } else {
            return null;
        }
    }
}