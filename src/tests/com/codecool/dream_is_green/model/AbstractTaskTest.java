package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTaskTest {

    AbstractTask abstractTask;

    @BeforeEach
    public void initAbstractTask() {
        this.abstractTask = new AbstractTask("task", 200, new AbstractTaskCategory("category"));
    }

    @Test
    public void testGetTitle() {
        assertEquals("task", this.abstractTask.getTitle());
    }

    @Test
    public void testGetPrice() {
        assertEquals(Integer.valueOf(200), this.abstractTask.getPrice());
    }

    @Test
    public void testGetCategory() {
        assertEquals(AbstractTaskCategory.class, this.abstractTask.getCategory().getClass());
    }
}