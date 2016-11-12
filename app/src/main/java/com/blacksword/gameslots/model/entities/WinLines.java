package com.blacksword.gameslots.model.entities;

import java.util.ArrayList;
import static com.blacksword.gameslots.model.constants.Constants.*;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class WinLines {
    private ArrayList<Integer> winLines = new ArrayList();

    /**
     * returns line number by its index
     * @param index
     * @return
     */
    public int getWinLineNumber(int index){
        if (index <= getLastLineIndex() && index >= 0){
            return this.winLines.get(index);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public ArrayList<Integer> getWinLinesArray(){
        if(winLines.size() <= 0){
            winLines.add(DEFAULT_PAY_LINE);
        }
        return winLines;
    }

    /**
     * returns the index of the last line number
     * @return
     */
    public int getLastLineIndex(){
        return this.winLines.size() - 1;
    }

    /**
     * sets new line number to the array list
     * @param winLineNumber
     */
    public void setWinLine(int winLineNumber){
        if (!winLines.contains(winLineNumber)){
            this.winLines.add(winLineNumber);
        }
    }

    /**
     * deletes win line from the array
     */
    public void deleteWinLine(Integer lineNumber){
        this.winLines.remove(lineNumber);
    }
}
