package com.blacksword.gameslots.model.constants;

import com.blacksword.gameslots.model.entities.Tile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zakhar on 06.11.2016.
 */

public class Constants {
    public final static int FIRST_ELEMENT_NUMBER = 0;
    public final static int SECOND_ELEMENT_NUMBER = 1;
    public final static int LAST_ELEMENT_NUMBER = 10;
    public final static int SLOTS_NUMBER = 5;
    public final static int SLOT_ELEMENTS_NUMBER = 3;//how many elements do we see on the display
    public final static int EMPTY_COLLECTION_NUMBER = 0;
    public final static int MAX_LINES_NUMBER = 4;
    public final static int MIN_LINES_NUMBER = 1;
    public final static float BET_SCALE_COEFFICIENT = 0.05f;
    public final static float BET_SCALE_FOR_ONE_LINE = 1f;
    public final static float DEFAULT_ELEMENT_WIN_COST = 0f;
    public final static float MIN_BET = 10f;
    public final static float MAX_SCATTER_BET = 50f;
    public final static float NULL_BET = 0f;
    public  final static int FREE_BETS_DEF = 0;
    public  final static int ONE_TILE_NUMBER_IN_LINE = -1;
    public  final static long SLOTS_RUNNING_TIME = 700;

    public final static Tile APPLE = new Tile("apple");
    public final static Tile BURGER = new Tile("burger");
    public final static Tile CARROT = new Tile("carrot");
    public final static Tile CHERRY = new Tile("cherry");
    public final static Tile ICE_CREAM = new Tile("ice-cream");
    public final static Tile EGG = new Tile("egg");
    public final static Tile MEAT = new Tile("meat");
    public final static Tile ORANGE = new Tile("orange");
    public final static Tile PIZZA = new Tile("pizza");
    public final static Tile PEAR = new Tile("pear");
    public final static Tile SCATTER = new Tile("scatter");
    public final static Tile WILD = new Tile("wild");

    public final static ArrayList<Tile> FOOD_ELEMENTS_ARRAY = new ArrayList<Tile>()
    {{
        add(APPLE);
        add(BURGER);
        add(CARROT);
        add(CHERRY);
        add(ICE_CREAM);
        add(EGG);
        add(MEAT);
        add(ORANGE);
        add(PIZZA);
        add(PEAR);
        add(SCATTER);
        add(WILD);
    }};

    public final static int DEFAULT_PAY_LINE = 0;

    public final static ArrayList<ArrayList<Integer>> PAY_LINES_MATRIX = new ArrayList<ArrayList<Integer>>()
    {{
        add(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1)));
        add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0)));
        add(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2)));
        add(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 1, 0)));
    }};

    public final static int ELEMENTS_WIN_COST_AMENDMENT = 2;

    public final static Map<String, ArrayList<Integer>> ELEMENTS_WIN_COST = new HashMap<String, ArrayList<Integer>>()
    {{
        put(APPLE.getName(), new ArrayList<Integer>(Arrays.asList(0, 3, 15, 60)));
        put(BURGER.getName(), new ArrayList<Integer>(Arrays.asList(0, 4, 20, 80)));
        put(CARROT.getName(), new ArrayList<Integer>(Arrays.asList(0, 5, 25, 100)));
        put(CHERRY.getName(), new ArrayList<Integer>(Arrays.asList(0, 8, 40, 150)));
        put(ICE_CREAM.getName(), new ArrayList<Integer>(Arrays.asList(0, 12, 50, 200)));
        put(EGG.getName(), new ArrayList<Integer>(Arrays.asList(0, 15, 75, 250)));
        put(MEAT.getName(), new ArrayList<Integer>(Arrays.asList(2, 25, 100, 400)));
        put(ORANGE.getName(), new ArrayList<Integer>(Arrays.asList(3, 30, 125, 600)));
        put(PIZZA.getName(), new ArrayList<Integer>(Arrays.asList(4, 35, 200, 1000)));
        put(PEAR.getName(), new ArrayList<Integer>(Arrays.asList(5, 50, 250, 1500)));
    }};

    public final static ArrayList<Integer> SCATTER_IN_LINE = new ArrayList<Integer>(Arrays.asList(0, 5, 10, 15));
    public final static ArrayList<Integer> WILD_IN_LINE = new ArrayList<Integer>(Arrays.asList(12, 120, 4000, 12000));

    public final static String ACCOUNT_DATA = "account";
    public  final static String DATE_DATA = "date";
    public final static String EMPTY_STRING = "";
    public final static float DAILY_MONEY = 20000;
    public final static float START_MONEY = 50000;
}
