package com.blacksword.gameslots.model.service;

import com.blacksword.gameslots.model.entities.Tile;
import com.blacksword.gameslots.model.entities.Tiles;
import static com.blacksword.gameslots.model.constants.Constants.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class TilesFactory {
    private Tiles tiles = new Tiles();

    /**
     * returns current random tiles
     * @return
     */
    public Tiles getTiles(){
        this.tiles = new Tiles();
        setSlotsImages();
        return tiles;
    }

    /**
     * returns the list (matrix) of slots images elements
     * @return
     */
    private void setSlotsImages(){
        for (int index = FIRST_ELEMENT_NUMBER; index < SLOTS_NUMBER; index++){
            tiles.setTileArrayToList(getSlotImg());
        }
    }

    /**
     * returns substring from food elements array
     * @return
     */
    private ArrayList getSlotImg(){
        int randomIndex = getRandomInt(LAST_ELEMENT_NUMBER);
        ArrayList<Tile> resultArray = new ArrayList();
        for (int index = FIRST_ELEMENT_NUMBER; index < SLOT_ELEMENTS_NUMBER; index++, randomIndex++){
            resultArray.add(FOOD_ELEMENTS_ARRAY.get(randomIndex));
            if (randomIndex >= FOOD_ELEMENTS_ARRAY.size() - 1){
                randomIndex = FIRST_ELEMENT_NUMBER - 1;
            }
        }
        return  resultArray;
    }

    /**
     * returns random digit from 0 to given number (inclusive)
     * @param rangeMax - given max number
     * @return int random (the number of elements)
     */
    private int getRandomInt(int rangeMax){
        Random random = new Random();
        return random.nextInt(rangeMax);
    }
}
