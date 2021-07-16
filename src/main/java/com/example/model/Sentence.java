package com.example.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents the domain model for a Sentence
 */
public class Sentence {

    /**
     * This element represents the words in a sentence through a collection of hashcode of the words.
     */
    private final Collection<Integer> wordHashes;

    /**
     * Constructor for Sentence
     */
    public Sentence() {
        wordHashes = new LinkedList<>();
    }

    /**
     * This method adds the hashcode for a word to wordHashes collection
     * @param wordHash: hashcode
     */
    public final void addWordHash(Integer wordHash) {
        wordHashes.add(wordHash);
    }

    /**
     * This method returns the collection of hashcode for all words in a sentence.
     * @return Collection<Integer>
     */
    public Collection<Integer> getWordHashes() {
        return wordHashes;
    }


}