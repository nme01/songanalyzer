package com.jwsolutions.songanalyzer.reportgenerartors;

import java.io.PrintStream;

public class SampleReportGenerator {
    private PrintStream printStream;

    public SampleReportGenerator(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Produces a simple report concerning
     */
    
    public void run() {
        this.printStream.println("Analysis: Total track time: <hours> hours, <mins> minutes, <secs> seconds");
        this.printStream.println("Analysis: Number of different collections: <number of distinct collections>");
        this.printStream.println("Analysis: Average price of a track <price>");
    }
}
