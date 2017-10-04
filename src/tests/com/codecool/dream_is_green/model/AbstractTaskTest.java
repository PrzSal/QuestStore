package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTaskTest {

    @Test
    public void testTitleIsNotNull() {
        AbstractTask abstractTask = new AbstractTask("task", 200, new AbstractTaskCategory("category"));
        assertNotNull(abstractTask.getTitle());
    }

    @Test
    public void testPriceIsNotNull() {
        AbstractTask abstractTask = new AbstractTask("task", 200, new AbstractTaskCategory("category"));
        assertNotNull(abstractTask.getPrice());
    }

    @Test
    public void testCategoryIsNotNull() {
        AbstractTask abstractTask = new AbstractTask("task", 200, new AbstractTaskCategory("category"));
        assertNotNull(abstractTask.getCategory());
    }
}