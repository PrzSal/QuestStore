package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTaskCategoryTest {

    @Test
    public void testNameIsNotNull() {
        AbstractTaskCategory abstractTaskCategory = new AbstractTaskCategory("category");
        assertNotNull(abstractTaskCategory.getName());
    }

    @Test
    public void testToString() {
        AbstractTaskCategory abstractTaskCategory = new AbstractTaskCategory("category");
        assertEquals("category", abstractTaskCategory.toString());
    }
}