package com.codecool.dream_is_green.model;

public class LevelModel {

    private String levelName;
    private Integer expRequired;

    public LevelModel (String levelName, Integer expRequired){

        this.levelName = levelName;
        this.expRequired = expRequired;
    }

    public String getLevelName() {
        return levelName;
    }

    public Integer getExpRequired() {
        return expRequired;
    }

    public String toString() {

        String str = String.format("%-16s %-16d", levelName, expRequired);

        return str;
    }


}
