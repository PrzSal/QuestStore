package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTaskCategoryTest {

    private AbstractTaskCategory abstractTaskCategory;

    @BeforeEach
    public void initAbstractTaskCategory() {
        this.abstractTaskCategory = new AbstractTaskCategory("category");
    }

    @Test
    public void testGetName() {
        assertEquals("category", this.abstractTaskCategory.getName());
    }

    @Test
    public void testToString() {
        assertEquals("category", abstractTaskCategory.toString());
    }
}