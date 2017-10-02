package com.codecool.dream_is_green.dao;

import java.io.File;

public class FactoryDAO {

    public void checkIsDatabase() {
        File database = new File("quest_store.db");
        boolean exists = database.exists();

        if (!exists) {
            createDatabase();
        }
    }
