package com.jwsolutions.songanalyzer;

import com.jwsolutions.songanalyzer.reportgenerartors.SampleReportGenerator;

public class SongAnalyzer {
    public static void main(String[] args) {
        SampleReportGenerator reportGenerator = new SampleReportGenerator(System.out, null, null, null, null);
        reportGenerator.run();
    }
}
