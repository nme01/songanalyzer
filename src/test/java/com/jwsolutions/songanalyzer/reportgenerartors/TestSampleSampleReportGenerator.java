package com.jwsolutions.songanalyzer.reportgenerartors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TestSampleSampleReportGenerator {

    private ByteArrayOutputStream byteArrayStream;

    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        this.byteArrayStream = new ByteArrayOutputStream();
        this.printStream = new PrintStream(this.byteArrayStream);
    }

    @Test
    @DisplayName("Should output statistics to the stream")
    void testReportGenerator() {
        SampleReportGenerator reportGenerator = new SampleReportGenerator(this.printStream);
        reportGenerator.run();

        final String expectedOutput =
                "Analysis: Total track time: <hours> hours, <mins> minutes, <secs> seconds"
                + System.getProperty("line.separator")
                + "Analysis: Number of different collections: <number of distinct collections>"
                + System.getProperty("line.separator")
                + "Analysis: Average price of a track <price>"
                + System.getProperty("line.separator");

        Assertions.assertEquals(expectedOutput, this.byteArrayStream.toString());
    }
}
