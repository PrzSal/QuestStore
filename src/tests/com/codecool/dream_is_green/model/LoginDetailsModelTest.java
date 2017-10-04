package com.codecool.dream_is_green.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginDetailsModelTest {

    @Test
    public void isUserTypeNotNull() {
        LoginDetailsModel details = new LoginDetailsModel(
                "user type", "haslo");
        assertNotNull(details.getUserType());
    }

    @Test
    public void isUserPasswordNotNull() {
        LoginDetailsModel details = new LoginDetailsModel(
                "user type", "haslo");
        assertNotNull(details.getUserPassword());
    }
}