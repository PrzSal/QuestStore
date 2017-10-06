package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreUserModelTest {

    private PreUserModel preUserModel;

    @BeforeEach
    void initPreUserModel() {
        this.preUserModel = new PreUserModel("testName", "testSurname", "testEmail", "testLogin", "testPassword", "testClassName");
    }

    @Test
    void testGetName() {
        assertEquals(this.preUserModel.getName(), "testName");
    }

    @Test
    void testGetSurname() {
        assertEquals(this.preUserModel.getSurname(), "testSurname");
    }

    @Test
    void testGetEmail() {
        assertEquals(this.preUserModel.getEmail(), "testEmail");
    }

    @Test
    void testGetLogin() {
        assertEquals(this.preUserModel.getLogin(), "testLogin");
    }

    @Test
    void testGetPassword() {
        assertEquals(this.preUserModel.getPassword(), "testPassword");
    }

    @Test
    void testGetClassName() {
        assertEquals(this.preUserModel.getClassName(), "testClassName");
    }

}