package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactModelTest {

    private ArtifactCategoryModel artifactCategory;
    private ArtifactModel artifactModel;

    @BeforeEach
    void initArtifactModel() {
        this.artifactCategory = new ArtifactCategoryModel("testName");
        this.artifactModel = new ArtifactModel("testTitle", 1000, artifactCategory);
    }

    @org.junit.jupiter.api.Test
    void testToStringIsUsedIsDefault() {

        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[ ]");
        assertEquals(actual, this.artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testToStringIsUsedEquals1() {

        this.artifactModel.setIsUsed(1);
        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[SEND]");
        assertEquals(actual, this.artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testToStringIsUsedEquals2() {

        this.artifactModel.setIsUsed(2);
        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[USED]");
        assertEquals(actual, this.artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testSetGetIsUsed() {

        Integer expected = 1;
        this.artifactModel.setIsUsed(expected);
        Integer actual = this.artifactModel.getIsUsed();

        assertEquals(expected, actual);
    }


}