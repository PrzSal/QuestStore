package com.codecool.dream_is_green.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UIViewTest {
    @Test
    void testPrintMessageString() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UIView uiView = new UIView();
        uiView.printMessage("message");
        String expectedOutput = "message\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintMessageInt() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UIView uiView = new UIView();
        uiView.printMessage(100);
        String expectedOutput = "100\n";

        assertEquals(expectedOutput, outContent.toString());
    }

}