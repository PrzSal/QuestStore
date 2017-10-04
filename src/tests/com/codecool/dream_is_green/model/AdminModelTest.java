package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminModelTest {

    @Test
    public void testFullNameIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getFullName());
    }

    @Test
    public void testLoginIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getLogin());
    }

    @Test
    public void testPasswordIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getPassword());
    }

    @Test
    public void testUserIDIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getUserID());
    }

    @Test
    public void testClassIsNotNull() {
        AdminModel adminModel = new AdminModel(1, "Kamil", "Postrożny",
                "wp@wp.pl", "ajax", "qwerty");
        assertNotNull(adminModel.getClass());
    }
}