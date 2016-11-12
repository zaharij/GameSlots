package com.blacksword.gameslots.model.constants;

import com.blacksword.gameslots.R;

import java.util.HashMap;

import static com.blacksword.gameslots.model.constants.Constants.*;

/**
 * Created by Zakhar on 09.11.2016.
 */

public class TilesManager {
    public final static HashMap<String, Integer> TILE_IMAGES_MAP = new HashMap<String, Integer>(){{
        put(APPLE.getName(), R.drawable.apple);
        put(BURGER.getName(), R.drawable.burger);
        put(CARROT.getName(), R.drawable.carrot);
        put(CHERRY.getName(), R.drawable.cherry);
        put(ICE_CREAM.getName(), R.drawable.ice_cream);
        put(EGG.getName(), R.drawable.egg);
        put(MEAT.getName(), R.drawable.meat);
        put(ORANGE.getName(), R.drawable.orange);
        put(PIZZA.getName(), R.drawable.pizza);
        put(PEAR.getName(), R.drawable.pear);
        put(SCATTER.getName(), R.drawable.scatter);
        put(WILD.getName(), R.drawable.wild);
    }};
}
