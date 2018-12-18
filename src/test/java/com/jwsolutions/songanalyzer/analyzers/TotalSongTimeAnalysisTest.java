package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSongTimeAnalysisTest {

    private TotalSongTimeAnalysis totalSongTimeAnalysis;

    @BeforeEach
    void setUp() {
        totalSongTimeAnalysis = new TotalSongTimeAnalysis();
    }

    @Test
    @DisplayName("Total song time should be 1 hour 4 minutes 30 seconds")
    void calculateTotalDuration() {
        List<SongInfo> songInfos = makeTestSongsList();
        Duration actualTotalDuration = totalSongTimeAnalysis.calculateTotalDuration(songInfos);
        Duration expectedTotalDuration = Duration.parse("PT1H4M30.0S");  // 1 hour 4 minutes 30 seconds
        assertEquals(expectedTotalDuration, actualTotalDuration);
    }

    @Test
    @DisplayName("For empty collection total song time should be 0")
    void testEmptyCollection() {
        List<SongInfo> songInfos = Collections.emptyList();
        Duration actualTotalDuration = totalSongTimeAnalysis.calculateTotalDuration(songInfos);
        Duration expectedTotalDuration = Duration.ZERO;
        assertEquals(expectedTotalDuration, actualTotalDuration);
    }

    private List<SongInfo> makeTestSongsList() {
        return List.of(
            new SongInfo(1L, Duration.ofMinutes(1L), "Test1", new Price("10", Currency.USD)),
            new SongInfo(2L, Duration.ofMinutes(2L), "Test1", new Price("20", Currency.USD)),
            new SongInfo(3L, Duration.ofSeconds(30L), "Test2", new Price("30", Currency.USD)),
            new SongInfo(4L, Duration.ofMinutes(1L), "Test3", new Price("40", Currency.USD)),
            new SongInfo(4L, Duration.ofHours(1L), "Test3", new Price("50", Currency.USD))
        );
    }
}