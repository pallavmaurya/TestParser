package com.example.utils;

import java.util.Arrays;
import java.util.List;

/**
 * This is a utility class for String operations required in the application
 */
public class ParserUtils {


    /**
     * List of supported sentence delimiters
     */
    private static final List<Character> sentenceDelimiters = Arrays.asList('!', '.', '?');

    /**
     * this method checks if input String is the last word in a sentence
     * @param word : input String
     * @param lastIndex: last index of the string
     * @return True if this word is appended with a sentence delimiter
     */
    public static boolean isLastWordOfSentence(String word, int lastIndex) {
        Character lastCharacter = word.charAt(lastIndex);
        return sentenceDelimiters.contains(lastCharacter);
    }

    /**
     * This method replaces "." in the honorifics to avoid being read as a sentence delimiter
     * @param line : input line as String
     * @return output String
     */
    public static String replaceHonorifics(String line) {
        return line.replace("Mr.", "Mr")
                .replace("Ms.", "Ms")
                .replace("Dr.", "Dr");
    }

    /**
     * This method restores "." in the honorifics.
     * @param word input String
     * @return String with restored honorifics
     */
    public static String restoreHonorifics(String word) {
        return word.replace("Mr", "Mr.")
                .replace("Ms", "Ms.")
                .replace("Dr", "Dr.");
    }

    /**
     * This methods checks if the input String is a valid word
     * i.e., it is not an empty word Or a Blank word ,Or word with only punctuation
     * @param lineItem input String
     * @return True if input is a valid word
     */
    public static boolean isValidWord(String lineItem) {
        boolean isValid;
        if (lineItem.isEmpty())
            isValid = false;
        else if (lineItem.equals(" "))
            isValid = false;
        else if (lineItem.equals(","))
            isValid = false;
        else isValid = !lineItem.equals(".");
        return isValid;
    }
}
