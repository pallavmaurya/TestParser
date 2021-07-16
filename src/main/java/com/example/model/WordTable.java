package com.example.model;

import java.util.Hashtable;

/**
 * This class is used to store the words read by MyTextParser into a hashtable, with their hashcode as key
 */
public class WordTable {

    /**
     * Hashtable to store the words read by MyTextParser, with their hashcode as key
     */
    private static Hashtable<Integer, String> wordHashTable;

    /**
     * Constructor for WordTable
     */
    public WordTable() {
        wordHashTable = new Hashtable<>();
    }

    /**
     * This method returns a String value of a word for given hashcode as key from wordHashTable
     * @param hashCode :
     * @return String
     */
    public static String getWord(Integer hashCode) {
        return wordHashTable.get(hashCode);
    }

    /**
     * This method stored a word in the wordHashTable with its hashcode as key
     * @param word : String representation for Word to be stored in hashtable
     * @return hashcode
     */
    public static Integer saveWord(String word) {
        Integer hashCode = word.hashCode();
        wordHashTable.put(hashCode, word);
        return hashCode;
    }
}
