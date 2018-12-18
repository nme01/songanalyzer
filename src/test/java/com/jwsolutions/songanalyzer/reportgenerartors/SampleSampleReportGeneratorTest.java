package com.jwsolutions.songanalyzer.reportgenerartors;

import com.jwsolutions.songanalyzer.analyzers.AverageSongPriceAnalysis;
import com.jwsolutions.songanalyzer.analyzers.DistinctCollectionsAnalysis;
import com.jwsolutions.songanalyzer.analyzers.TotalSongTimeAnalysis;
import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.Price;
import com.jwsolutions.songanalyzer.songinfoproviders.SongInfoProvider;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SampleSampleReportGeneratorTest {

    private ByteArrayOutputStream byteArrayStream;
    private SampleReportGenerator reportGenerator;

    private final Collection<SongInfo> songInfos = Collections.emptyList();
    private final Duration totalDuration = Duration.ofSeconds(120001); // 33h 20min 01s
    private final Price averagePrice = new Price(new BigDecimal("13.10"), Currency.USD);
    private final int distinctCollectionsNumber = 420;

    @BeforeEach
    void setUp() {
        this.byteArrayStream = new ByteArrayOutputStream();

        PrintStream printStream = new PrintStream(this.byteArrayStream);
        SongInfoProvider songInfoProvider = mockSongInfoProvider();

        TotalSongTimeAnalysis totalSongTimeAnalysis = mockTotalSongTimeAnalysis();
        DistinctCollectionsAnalysis distinctCollectionsAnalysis = mockDistinctCollectionsAnalysis();
        AverageSongPriceAnalysis averageSongPriceAnalysis = mockAverageSongPriceAnalysis();

        this.reportGenerator = new SampleReportGenerator(printStream,
                songInfoProvider, totalSongTimeAnalysis, distinctCollectionsAnalysis, averageSongPriceAnalysis
        );
    }

    private SongInfoProvider mockSongInfoProvider() {
        SongInfoProvider songInfoProvider = mock(SongInfoProvider.class);
        try {
            when(songInfoProvider.find(Mockito.any(SongQuery.class))).thenReturn(songInfos);
        } catch (IOException e) {
            // should never get here
            e.printStackTrace();
        }
        return songInfoProvider;
    }

    private TotalSongTimeAnalysis mockTotalSongTimeAnalysis() {
        TotalSongTimeAnalysis totalSongTimeAnalysis = mock(TotalSongTimeAnalysis.class);
        when(totalSongTimeAnalysis.calculateTotalDuration(songInfos)).thenReturn(totalDuration);
        return totalSongTimeAnalysis;
    }

    private DistinctCollectionsAnalysis mockDistinctCollectionsAnalysis() {
        DistinctCollectionsAnalysis distinctCollectionsAnalysis = mock(DistinctCollectionsAnalysis.class);
        when(distinctCollectionsAnalysis.countDistinctCollections(songInfos)).thenReturn(distinctCollectionsNumber);
        return distinctCollectionsAnalysis;
    }

    private AverageSongPriceAnalysis mockAverageSongPriceAnalysis() {
        AverageSongPriceAnalysis averageSongPriceAnalysis = mock(AverageSongPriceAnalysis.class);
        when(averageSongPriceAnalysis.calculateAveragePrice(songInfos)).thenReturn(averagePrice);
        return averageSongPriceAnalysis;
    }

    @Test
    @DisplayName("Should output statistics to the stream")
    void testReportGenerator() {
        reportGenerator.run();

        final String expectedOutput =
                "Analysis: Total track time: 33 hours, 20 minutes, 1 second"
                        + System.getProperty("line.separator")
                        + "Analysis: Number of different collections: " + distinctCollectionsNumber
                        + System.getProperty("line.separator")
                        + "Analysis: Average price of a track " + averagePrice
                        + System.getProperty("line.separator");

        Assertions.assertEquals(expectedOutput, this.byteArrayStream.toString());
    }
}
