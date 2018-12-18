package com.jwsolutions.songanalyzer.configuration;

import com.jwsolutions.songanalyzer.analyzers.AverageSongPriceAnalysis;
import com.jwsolutions.songanalyzer.analyzers.DistinctCollectionsAnalysis;
import com.jwsolutions.songanalyzer.analyzers.TotalSongTimeAnalysis;
import com.jwsolutions.songanalyzer.reportgenerartors.SampleReportGenerator;
import com.jwsolutions.songanalyzer.songinfoproviders.SongInfoProvider;
import com.jwsolutions.songanalyzer.songinfoproviders.itunes.MockedITunesSongInfoProvider;

import java.io.PrintStream;
import java.net.URL;

/**
 * A configuration for the {@link SampleReportGenerator}. If it gets too complex, should be substituted with
 * a proper IoC container.
 */
public class Configuration {
    public static SampleReportGenerator makeSampleReportGenerator(URL mockedDataUrl) {
        PrintStream outputStream = System.out;
        SongInfoProvider songInfoProvider = new MockedITunesSongInfoProvider(mockedDataUrl);
        TotalSongTimeAnalysis totalSongTimeAnalysis = new TotalSongTimeAnalysis();
        DistinctCollectionsAnalysis distinctCollectionsAnalysis = new DistinctCollectionsAnalysis();
        AverageSongPriceAnalysis averageSongPriceAnalysis = new AverageSongPriceAnalysis();

        return new SampleReportGenerator(
            outputStream,
            songInfoProvider,
            totalSongTimeAnalysis,
            distinctCollectionsAnalysis,
            averageSongPriceAnalysis
        );
    }
}
