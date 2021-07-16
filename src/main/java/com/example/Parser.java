package com.example;

import com.example.model.Sentence;
import com.example.model.WordTable;
import com.example.utils.ParserUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is am implementation of a Parser
 */
public class Parser {

    private static final String PUNCTUATIONS_REGEX = "\\s|,|\\(|\\)|:|-";
    private static final String NO_SUCH_FILE_EXISTS = "Please provide correct relative file path, no such file exists: ";
    private final static Logger logger = Logger.getLogger(Parser.class.getName());

    private static WordTable wordTable;

    private static PrintWriter printWriter;
    private static String inputFile;

    /**
     * Constructor for Parser
     * @param inputFile : the input file to be parsed
     * @param printWriter: printWriter to write output file
     */
    public Parser(String inputFile, PrintWriter printWriter) {
        Parser.inputFile = inputFile;
        Parser.printWriter = printWriter;
    }

    /**
     * This is the public method inside Parser to Parse an input file and print using printWriter
     * @throws IOException: {@link IOException}
     */
    public void parse() throws IOException {
        logger.log(Level.INFO,"Starting parse()");

        wordTable = new WordTable();
        Path path = Paths.get(inputFile);
        try {
            try (Stream<String> lines = Files.lines(path)) {
                processLineStream(lines);
                logger.log(Level.INFO,"Parsing finished");
            } catch (NoSuchFileException exception) {
                logger.log(Level.SEVERE,NO_SUCH_FILE_EXISTS +  exception.getMessage());
                System.out.println(NO_SUCH_FILE_EXISTS + exception.getMessage());
            }
        } catch (FileNotFoundException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            System.out.println(exception.getMessage());
            System.exit(1);
        } finally {
            printWriter.close();
        }
    }

    /**
     * This method breaks the input lines into words, validates them and invokes processWord for Each word
     * @param lines : Stream of lines as Strings
     */
    private static void processLineStream(Stream<String> lines) {
        AtomicInteger index;
        AtomicReference<Sentence> sentence;
        final AtomicReference<Pattern> pattern;

        logger.log(Level.INFO,"processing lineStream");
        index = new AtomicInteger();
        index.set(1);
        sentence = new AtomicReference<>(new Sentence());
        pattern = new AtomicReference<>(Pattern.compile(PUNCTUATIONS_REGEX));
        lines.map(ParserUtils::replaceHonorifics)
                .forEach(line -> {
                    try (final Stream<String> spitLineStream = pattern.get().splitAsStream(line)) {
                        spitLineStream.map(String::trim)
                                .filter(ParserUtils::isValidWord)
                                .forEach(word -> processWord(index, sentence, word));
                    }
                });
        logger.log(Level.INFO,"processLineStream finished");
        lines.close();
    }


    /**
     * This method processes the words of a sentence.
     * If the input word is the last word then it is added to sentence and the sentence object is printed and reset.
     * If the input word is not the last word then it is added to sentence.
     * @param index: index of the sentence in input file
     * @param sentence : Sentence object
     * @param word: String representation of a word in a Sentence
     */
    private static void processWord(AtomicInteger index, AtomicReference<Sentence> sentence, String word) {
        int lastIndex = word.length() - 1;
        if (ParserUtils.isLastWordOfSentence(word, lastIndex)) {
            sentence.get().addWordHash(WordTable.saveWord(word.substring(0, lastIndex)));
            printSentenceAsCsv(sentence.get(), index.get());
            index.getAndIncrement();
            sentence.set(new Sentence());
        } else
            sentence.get().addWordHash(WordTable.saveWord(word));
    }

    /**
     * This method prints the Sentence as csv
     * @param sentence : Sentence object
     * @param index: index of the sentence in input file
     */
    private static void printSentenceAsCsv(Sentence sentence, int index) {
        String prefix = "Sentence " + index + ",";
        String sentenceAsCsv = convertToCSV(sentence);
        printWriter.println(prefix + sentenceAsCsv);
    }

    /**
     * This method returns a comma separated String representation of a Sentence
     * @param sentence : Sentence object
     * @return comma separated String representation of a Sentence
     */
    private static String convertToCSV(Sentence sentence) {
        return sentence.getWordHashes()
                .stream()
                .map(WordTable::getWord)
                .map(ParserUtils::restoreHonorifics)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(","));
    }

}
