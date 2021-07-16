package com.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final String outputFile = "output.csv";
    private static final String PLEASE_FIND_THE_OUTPUT_FILE_AT_PATH = "Please find the output file at path : ";
    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * This program takes an input file path as input and generates output file as output.csv
     *
     * @param args input file name from command line
     * @throws IOException: {@link IOException}
     */
    public static void main(String[] args) throws IOException {

        logger.log(Level.INFO, "Inside main");

        File csvOutputFile = new File(outputFile);

        PrintWriter printWriter = new PrintWriter(csvOutputFile);

        Parser parser = new Parser(args[0], printWriter);

        logger.log(Level.INFO, "Calling parser for file at path " + args[0]);

        parser.parse();

        logger.log(Level.INFO, "Parsing completed for file at path " + args[0]);

        System.out.println(PLEASE_FIND_THE_OUTPUT_FILE_AT_PATH + outputFile);
    }
}