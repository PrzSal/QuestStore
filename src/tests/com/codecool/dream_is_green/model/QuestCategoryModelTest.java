package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestCategoryModelTest {
    @Test
    void testToString() {
        QuestCategoryModel questCategoryModel = new QuestCategoryModel("testName");

        assertEquals("testName", questCategoryModel.toString());
    }

}