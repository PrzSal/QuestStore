package com.codecool.dream_is_green.model;

import junit.framework.TestCase;

public class ArtifactCategoryModelTest extends TestCase {
    public void testToString() {
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel("testName");
        String actualToString = artifactCategory.toString();
        assertEquals("testName", actualToString);
    }

}