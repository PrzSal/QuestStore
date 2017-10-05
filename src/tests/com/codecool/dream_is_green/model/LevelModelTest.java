package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelModelTest {

    private LevelModel levelModel;

    @BeforeEach
    void initLevelModel() {
        this.levelModel = new LevelModel("testName", 1000);
    }


    @Test
    void testGetLevelName() {
        assertEquals("testName", this.levelModel.getLevelName());
    }

    @Test
    void getExpRequired() {

        Integer actualExpRequired = this.levelModel.getExpRequired();
        Integer expectedExpRequired = 1000;
        assertEquals(expectedExpRequired, actualExpRequired);
    }

    @Test
    void testToString() {
        assertEquals(String.format("%-16s %-16d", "testName", 1000), this.levelModel.toString());
    }

}