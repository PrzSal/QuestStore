package com.codecool.dream_is_green.model;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactModelTest {

    @org.junit.jupiter.api.Test
    void testToStringIsUsedIsDefault() {
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel("testName");
        ArtifactModel artifactModel = new ArtifactModel("testTitle", 1000, artifactCategory);

        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[ ]");

        assertEquals(actual, artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testToStringIsUsedEquals1() {
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel("testName");
        ArtifactModel artifactModel = new ArtifactModel("testTitle", 1000, artifactCategory);
        artifactModel.setIsUsed(1);

        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[SEND]");

        assertEquals(actual, artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testToStringIsUsedEquals2() {
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel("testName");
        ArtifactModel artifactModel = new ArtifactModel("testTitle", 1000, artifactCategory);
        artifactModel.setIsUsed(2);

        String actual = String.format("%-24s %-12d %-20s %-12s", "testTitle", 1000, "testName", "[USED]");

        assertEquals(actual, artifactModel.toString());
    }

    @org.junit.jupiter.api.Test
    void testSetGetIsUsed() {
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel("testName");
        ArtifactModel artifactModel = new ArtifactModel("testTitle", 1200, artifactCategory);

        Integer valueToTest = 1;
        artifactModel.setIsUsed(valueToTest);
        Integer actual = artifactModel.getIsUsed();

        assertEquals(valueToTest, actual);
    }


}