package com.jwsolutions.songanalyzer.reportgenerartors;

import com.jwsolutions.songanalyzer.analyzers.AverageSongPriceAnalysis;
import com.jwsolutions.songanalyzer.analyzers.DistinctCollectionsAnalysis;
import com.jwsolutions.songanalyzer.analyzers.TotalSongTimeAnalysis;
import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Price;
import com.jwsolutions.songanalyzer.songinfoproviders.SongInfoProvider;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQuery;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQueryBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Collection;

/**
 * Generates a sample report including basic statistics of the songs of Jack Johnson.
 */
public class SampleReportGenerator {
    private final PrintStream printStream;

    private final SongInfoProvider songInfoProvider;
    private final TotalSongTimeAnalysis totalSongTimeAnalysis;
    private final DistinctCollectionsAnalysis distinctCollectionsAnalysis;
    private final AverageSongPriceAnalysis averageSongPriceAnalysis;

    public SampleReportGenerator(PrintStream printStream,
                                 SongInfoProvider songInfoProvider,
                                 TotalSongTimeAnalysis totalSongTimeAnalysis,
                                 DistinctCollectionsAnalysis distinctCollectionsAnalysis,
                                 AverageSongPriceAnalysis averageSongPriceAnalysis) {
        this.printStream = printStream;
        this.songInfoProvider = songInfoProvider;

        this.totalSongTimeAnalysis = totalSongTimeAnalysis;
        this.distinctCollectionsAnalysis = distinctCollectionsAnalysis;
        this.averageSongPriceAnalysis = averageSongPriceAnalysis;
    }

    /**
     * Produces a simple report containing basic song statistics for the songs found by the "jack jackson" query.
     */
    public void run() {
        SongQuery query = new SongQueryBuilder()
                .append("jack")
                .append("johnson")
                .build();

        final Collection<SongInfo> songInfos;
        try {
            songInfos = songInfoProvider.find(query);
        } catch (IOException e) {
            System.err.println("Couldn't read the data needed to generate the " + this.getClass().getSimpleName());
            e.printStackTrace();
            return;
        }

        printTotalDuration(songInfos);
        printDistinctCollectionsNumber(songInfos);
        printAveragePrice(songInfos);
    }

    private void printAveragePrice(Collection<SongInfo> songInfos) {
        Price avgPrice = averageSongPriceAnalysis.calculateAveragePrice(songInfos);
        avgPrice = avgPrice.setScale(2, RoundingMode.HALF_UP);
        this.printStream.println("Analysis: Average price of a track " + avgPrice.toString());
    }

    private void printDistinctCollectionsNumber(Collection<SongInfo> songInfos) {
        int numOfDistinctCollections = distinctCollectionsAnalysis.countDistinctCollections(songInfos);
        this.printStream.println("Analysis: Number of different collections: " + numOfDistinctCollections);
    }

    private void printTotalDuration(Collection<SongInfo> songInfos) {
        final Duration totalDuration = totalSongTimeAnalysis.calculateTotalDuration(songInfos);

        final long hours = totalDuration.toHours();
        final int minutes = totalDuration.toMinutesPart();
        final int seconds = totalDuration.toSecondsPart();

        String message = "Analysis: Total track time: " +
                hours + (hours != 1 ? " hours" : " hour") + ", " +
                minutes + (minutes != 1 ? " minutes" : " minute") + ", " +
                seconds + (seconds != 1 ? " seconds" : " second");

        this.printStream.println(message);
    }
}
