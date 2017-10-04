package com.codecool.dream_is_green.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestModelTest {

    @Test
    public void toStringFormatTest() {
        QuestModel quest = new QuestModel("questName", 500,
                new QuestCategoryModel("categoryName"));

        assertEquals(String.format("%-24s %-12d %-20s",
                "questName", 500, "categoryName"), quest.toString());
    }


}