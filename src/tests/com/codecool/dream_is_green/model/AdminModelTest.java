package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminModelTest {

    @Test
    public void testNameIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getName());
    }
}