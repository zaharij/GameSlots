package com.blacksword.gameslots.model.service;

import com.blacksword.gameslots.model.entities.Tiles;
import com.blacksword.gameslots.model.entities.WinLines;
import com.blacksword.gameslots.model.entities.WinTilesCombinations;
import java.util.HashMap;
import java.util.Random;

import static com.blacksword.gameslots.model.constants.Constants.*;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class SlotsFoodModel {
    private Tiles tiles;
    private WinTilesCombinations winTilesCombinations;
    private TilesFactory tilesFactory = new TilesFactory();
    private WinLines winLines = new WinLines();
    private int freeBetsNumber = FREE_BETS_DEF;

    public Tiles getTiles() {
        return tiles;
    }

    public void setFreeBetsNumber(int freeBetsNumber) {
        this.freeBetsNumber = freeBetsNumber;
    }

    public int getFreeBetsNumber() {
        return freeBetsNumber;
    }

    public WinLines getWinLines() {
        return winLines;
    }

    public void addWinLine (int winLineNumber){
        winLines.setWinLine(winLineNumber);
    }

    public void removeWinLine(Integer lineNumber){
        winLines.deleteWinLine(lineNumber);
    }

    /**
     * initialises tiles
     */
    private void setTiles(){
        this.tiles = tilesFactory.getTiles();
    }

    /**
     * returns the result of game
     * true if win, and false in other case
     * @return boolean result
     */
    private boolean isWin() {
        winTilesCombinations = new WinTilesCombinations();
        setTiles();
        boolean result = false;
        String temp;
        HashMap<String, Integer> winMap;
        HashMap<String, Integer> wildMap;
        for (int winLine : winLines.getWinLinesArray()) {
            temp = null;
            winMap = new HashMap<>();
            wildMap = null;
            wildMap = getWilds(tiles, winLine);
            for (int slotsImgIndex = FIRST_ELEMENT_NUMBER; slotsImgIndex < SLOTS_NUMBER; slotsImgIndex++) {
                temp = tiles.getTile(slotsImgIndex, PAY_LINES_MATRIX.get(winLine).get(slotsImgIndex)).getName();
                int tileCount = ONE_TILE_NUMBER_IN_LINE;
                if(!temp.equals(WILD.getName())) {
                    tileCount = winMap.containsKey(temp) ? winMap.get(temp) + 1 : ONE_TILE_NUMBER_IN_LINE;
                    winMap.put(temp, tileCount);
                }
                if (tileCount > ONE_TILE_NUMBER_IN_LINE || temp.equals(WILD.getName())){
                    result = true;
                }
            }
            if (wildMap.size() > EMPTY_COLLECTION_NUMBER){
                int wildsNumber = wildMap.get(WILD.getName());
                String tilePlusWildName = getBestWildCombination(winMap, wildsNumber);
                if (!tilePlusWildName.equals(WILD.getName())) {
                    int newTilesNumber = wildsNumber + winMap.get(tilePlusWildName);
                    winMap.put(tilePlusWildName, newTilesNumber);
                } else {
                    wildsNumber--;
                    winMap.put(WILD.getName(), wildsNumber);
                }
            }
            winTilesCombinations.setWinLine(winMap);
        }
        return result;
    }

    /**
     * returns the name of the tile
     * @param winMap
     * @return
     */
    public String getBestWildCombination(HashMap<String, Integer> winMap, int wilds){
        int wildsNumber = wilds;
        int winCost = 0;
        int winCostTemp = 0;
        String resultTile = null;
        for (String currentTile: winMap.keySet()){
            if (!currentTile.equals(SCATTER.getName())) {
                winCost = ELEMENTS_WIN_COST.get(currentTile).get(winMap.get(currentTile) + wilds);
                if (resultTile == null || winCost > winCostTemp) {
                    resultTile = currentTile;
                    winCostTemp = winCost;
                }
            }
        }
        wildsNumber -= ELEMENTS_WIN_COST_AMENDMENT;
        if (wildsNumber >= FIRST_ELEMENT_NUMBER &&WILD_IN_LINE.get(wildsNumber) > winCost && WILD_IN_LINE.get(wildsNumber) > winCostTemp){
            resultTile = WILD.getName();
        }
        return resultTile;
    }

    /**
     * returns all wilds in the line
     * @return
     */
    private HashMap<String, Integer> getWilds(Tiles tiles, int winLine){
        HashMap<String, Integer> wildMap = new HashMap<String, Integer>();
        int wildCount = EMPTY_COLLECTION_NUMBER;
        for (int slotsImgIndex = FIRST_ELEMENT_NUMBER; slotsImgIndex < SLOTS_NUMBER; slotsImgIndex++) {
            if(tiles.getTile(slotsImgIndex, PAY_LINES_MATRIX.get(winLine).get(slotsImgIndex)).getName().equals(WILD.getName())){
                wildCount++;
                wildMap.put(WILD.getName(), wildCount);
            }
        }
        return wildMap;
    }

    /**
     * returns the number of money that user has been won
     * @return double (the number of money)
     */
    private float getElementsWinCost(){
        float result = DEFAULT_ELEMENT_WIN_COST;
        if (isWin()){
            for(int count = FIRST_ELEMENT_NUMBER; count <= winTilesCombinations.getLastMapIndex(); count++) {
                for (String currentElement : winTilesCombinations.getWinTilesMap(count).keySet()) {
                    if(winTilesCombinations.getWinTilesMap(count).get(currentElement) >= 0) {
                        if (currentElement.equals(SCATTER.getName())) {
                            freeBetsNumber += SCATTER_IN_LINE.get(winTilesCombinations.getWinTilesMap(count).get(currentElement));
                        } else if(currentElement.equals(WILD.getName())) {
                            result += WILD_IN_LINE.get(winTilesCombinations.getWinTilesMap(count).get(currentElement));
                        } else {
                            result += ELEMENTS_WIN_COST.get(currentElement).get(winTilesCombinations.getWinTilesMap(count).get(currentElement));
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * returns free bet
     * @param minBet
     * @param maxBet
     * @return
     */
    public float getFreeBet(float minBet, float maxBet){
        int minBetInt = (int) minBet;
        int maxBetInt = (int) maxBet;
        int randomBet = new Random(System.currentTimeMillis()).nextInt(maxBetInt - minBetInt) + minBetInt;
        float freeBet = 0;
        if(randomBet <= 10){
            return 10;
        } else if(randomBet > 10 && randomBet <= 20){
            return 20;
        } else if (randomBet > 20 && randomBet <= 50){
            return 50;
        } else {
            return 100;
        }
    }

    /**
     * returns win money
     * @param bet - user's bet
     * @return money (float)
     */
    public float getWinMoney(float bet){
        float betScale = BET_SCALE_FOR_ONE_LINE;
        float winCost = getElementsWinCost();
        float winMoney;
        if (winCost > DEFAULT_ELEMENT_WIN_COST && bet >= MIN_BET) {
            for (int index = SECOND_ELEMENT_NUMBER; index <= winLines.getLastLineIndex(); index++) {
                betScale -= BET_SCALE_COEFFICIENT;
            }
            winMoney = (winCost * betScale) + bet;
        } else if (winCost == DEFAULT_ELEMENT_WIN_COST && bet >= MIN_BET){
            winMoney = - bet;
        } else {
            winMoney = DEFAULT_ELEMENT_WIN_COST;
        }
        return winMoney;
    }
}
