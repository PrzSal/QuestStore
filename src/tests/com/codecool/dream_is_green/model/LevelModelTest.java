package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelModelTest {

    @Test
    void testGetLevelName() {
        LevelModel levelModel = new LevelModel("testName", 1000);

        assertEquals("testName", levelModel.getLevelName());
    }

    @Test
    void getExpRequired() {
        LevelModel levelModel = new LevelModel("testName", 1000);

        Integer actualExpRequired = levelModel.getExpRequired();
        Integer expectedExpRequired = 1000;
        assertEquals(expectedExpRequired, actualExpRequired);
    }

    @Test
    void testToString() {
        LevelModel levelModel = new LevelModel("testName", 1000);

        assertEquals(String.format("%-16s %-16d", "testName", 1000), levelModel.toString());
    }

}