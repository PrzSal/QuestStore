package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.LevelModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LevelDAOTest {

    private LevelModel levelModel;
    private LevelDAO levelDAO;

    @BeforeEach
    void initLevel() {
         this.levelModel = new LevelModel("levelName", 10);
         this.levelDAO = new LevelDAO();
    }

    @Test
    void testAddObject() {
        this.levelDAO.addObject(this.levelModel);
        assertEquals(this.levelModel, this.levelDAO.getObjectList().get(0));
    }

    @Test
    void testRemoveObject() {
        this.levelDAO.addObject(this.levelModel);
        this.levelDAO.removeObject(this.levelModel);
        assertThrows(IndexOutOfBoundsException.class, ()-> {
            this.levelDAO.getObjectList().get(0);
        });
    }

    @Test
    void testGetObjectsList() {
        assertNotNull(this.levelDAO.getObjectList());
    }

    @Test
    void testToString() {
        this.levelDAO.addObject(this.levelModel);
        assertEquals(this.levelDAO.toString(), "1. | levelName        10");
    }

}