package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MentorModelTest {

    private MentorModel mentorModel;

    @BeforeEach
    void initMentorModel() {
        this.mentorModel = new MentorModel(1, "testName",
                                          "testSurname", "testEmail",
                                          "testLogin", "testPassword",
                                          "testClassName");
    }

    @Test
    void testSetClassName() {
        this.mentorModel.setClassName("SuperClassName");

        assertEquals(mentorModel.getClassName(), "SuperClassName");
    }

    @Test
    void testGetName() {
        assertEquals(this.mentorModel.getName(), "testName");
    }

    @Test
    void testGetEmail() {
        assertEquals(this.mentorModel.getEmail(), "testEmail");
    }

    @Test
    void testGetUserID() {
        assertEquals(this.mentorModel.getUserID(), 1);
    }

    @Test
    void testGetFullName() {
        assertEquals(this.mentorModel.getFullName(), "testName testSurname");
    }

    @Test
    void testGetLogin() {
        assertEquals(this.mentorModel.getLogin(), "testLogin");
    }

    @Test
    void testGetPassword() {
        assertEquals(this.mentorModel.getPassword(), "testPassword");
    }

    @Test
    void testSetEmail() {
        MentorModel mentorModel = new MentorModel(1, "testName",
                                                  "testSurname", "testEmail",
                                                  "testLogin", "testPassword",
                                                  "testClassName");

        mentorModel.setEmail("newEmail@gmail.com");
        assertEquals(mentorModel.getEmail(), "newEmail@gmail.com");
    }

    @Test
    void testSetLogin() {
        MentorModel mentorModel = new MentorModel(1, "testName",
                                                  "testSurname", "testEmail",
                                                  "testLogin", "testPassword",
                                                  "testClassName");

        mentorModel.setLogin("newLogin");
        assertEquals(mentorModel.getLogin(), "newLogin");
    }

    @Test
    void testSetPassword() {
        MentorModel mentorModel = new MentorModel(1, "testName",
                                                  "testSurname", "testEmail",
                                                  "testLogin", "testPassword",
                                                  "testClassName");

        mentorModel.setPassword("newPassword");
        assertEquals(mentorModel.getPassword(), "newPassword");
    }

    @Test
    void testToString() {
        String expected = String.format("%-8s %-12s %-12s %-24s %-12s %-12s %-12s",
                                        1, "testName", "testSurname", "testEmail",
                                        "testClassName", "testLogin", "testPassword");
        assertEquals(this.mentorModel.toString(), expected);
    }

}