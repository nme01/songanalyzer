package com.jwsolutions.songanalyzer;

import com.jwsolutions.songanalyzer.configuration.Configuration;
import com.jwsolutions.songanalyzer.reportgenerartors.SampleReportGenerator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SongAnalyzer {
    public static void main(String[] args) {
        URL mockedDataUrl = parseInputArguments(args);
        if (mockedDataUrl == null) {
            System.exit(-1);
        }

        SampleReportGenerator reportGenerator = Configuration.makeSampleReportGenerator(mockedDataUrl);
        reportGenerator.run();
    }

    private static URL parseInputArguments(String[] args) {
        if (args.length != 1) {
            System.err.println(
                "Invalid command line arguments. Expected one argument:\n" +
                "- path to the file containing mocked iTunes data"
            );
        }

        String filePath = args[0];
        try {
            return new File(filePath).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("Malformatted file path");
        }

        return null;
    }
}
