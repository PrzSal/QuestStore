package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassModelTest {

    @Test
    public void testToString() {
        ClassModel classModel = new ClassModel("First");
        assertEquals("First", classModel.toString());
    }
}