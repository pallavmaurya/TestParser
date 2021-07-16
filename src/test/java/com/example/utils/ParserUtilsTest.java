package com.example.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserUtilsTest {

    @Test
    public void isLastWordOfSentence() {
        assertFalse("Failed to identify last word of sentence ", ParserUtils.isLastWordOfSentence("Mr", "Mr".length() - 1));
        assertTrue("Failed to identify last word of sentence ", ParserUtils.isLastWordOfSentence("outside.", "outside.".length() - 1));
        assertTrue("Failed to identify last word of sentence ", ParserUtils.isLastWordOfSentence(".", 0));
    }


    @Test
    public void replaceHonorifics() {
        assertEquals("Failed to replace Honorifics", "Mr and Ms Smith met Dr Jekyll outside.", ParserUtils.replaceHonorifics("Mr. and Ms. Smith met Dr. Jekyll outside."));
    }

    @Test
    public void restoreHonorifics() {
        assertEquals("Failed to replace Honorifics", "Mr. and Ms. Smith met Dr. Jekyll outside.", ParserUtils.restoreHonorifics("Mr and Ms Smith met Dr Jekyll outside."));
    }

    @Test
    public void isValidWord() {
        assertFalse("Failed to invalidate inValid word ", ParserUtils.isValidWord(""));
        assertFalse("Failed to invalidate inValid word ", ParserUtils.isValidWord(" "));
        assertFalse("Failed to invalidate inValid word ", ParserUtils.isValidWord(","));
        assertFalse("Failed to invalidate inValid word ", ParserUtils.isValidWord("."));
        assertTrue("Failed to validate Valid word ", ParserUtils.isValidWord("normal"));
    }

}