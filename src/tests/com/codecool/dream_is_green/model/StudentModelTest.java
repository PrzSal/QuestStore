package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentModelTest {

    private StudentModel studentModel;

    @BeforeEach
    void initStudentModel() {
        this.studentModel = new StudentModel(1, "testName", "testSurname",
                                             "testEmail", "testLogin",
                                             "testPassword", "testClassName", 10);

    }

    @Test
    void testGetWallet() {
        assertNotNull(this.studentModel.getWallet());
    }

    @Test
    void testGetExperience() {
        Integer expected = 10;
        assertEquals(this.studentModel.getExperience(), expected);
    }

    @Test
    void testToString() {
        String expected = String.format("%-8s %-12s %-12s %-24s %-12s %-12s %-12s %-12s",
                                        1, "testName",
                                        "testSurname", "testEmail",
                                        "testClassName", "null",
                                        "testLogin", "testPassword");
        assertEquals(this.studentModel.toString(), expected);
    }

}