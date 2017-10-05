package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDetailsModelTest {
    private LoginDetailsModel details;

    @BeforeEach
    public void initDetails() {
        this.details = new LoginDetailsModel(
                "user type", "haslo");
    }

    @Test
    public void testUserType() {
        String expected = "user type";
        assertEquals(expected, this.details.getUserType());
    }

    @Test
    public void testUserPassword() {
        String expected = "haslo";
        assertEquals(expected, this.details.getUserPassword());
    }
}
