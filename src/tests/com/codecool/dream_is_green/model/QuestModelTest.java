package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestModelTest {

    @Test
    public void toStringFormatTest() {
        QuestModel quest = new QuestModel("questName", 500,
                new QuestCategoryModel("categoryName"));

        assertEquals(String.format("%-24s %-12d %-20s",
                "questName", 500, "categoryName"), quest.toString());
    }
}