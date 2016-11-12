package com.blacksword.gameslots.model.entities;

/**
 * Created by Zakhar on 08.11.2016.
 */

public class Tile {
    private String name;
    private float chance;

    public Tile(){
    }

    public Tile(String name, float chance){
        this.name = name;
        this.chance = chance;
    }

    public Tile(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getChance() {
        return chance;
    }
}
