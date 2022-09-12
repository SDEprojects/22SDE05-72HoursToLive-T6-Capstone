package main.java.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TextParserTest {


    @Test
    public void getCommands() {
        try {
            TextParser parser = new TextParser();
            Response response = parser.getCommands("Go nOrth");
            assertEquals("go", response.getVerb());
            assertEquals("north", response.getLocation());
            assertNotEquals("south", response.getLocation());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCommands2() {
        try {
            TextParser parser = new TextParser();
            Response response = parser.getCommands("attack");
            assertEquals("attack", response.getVerb());
            assertNotEquals("go", response.getVerb());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCommands3() {
        try {
            TextParser parser = new TextParser();
            Response response = parser.getCommands("     ");
            assertFalse(response.isValid());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}