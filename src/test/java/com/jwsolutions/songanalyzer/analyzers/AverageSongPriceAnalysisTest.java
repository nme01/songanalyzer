package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.DifferentCurrenciesException;
import com.jwsolutions.songanalyzer.domain.money.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageSongPriceAnalysisTest {

    private AverageSongPriceAnalysis averageSongPriceAnalysis;

    @BeforeEach
    void setUp() {
        averageSongPriceAnalysis = new AverageSongPriceAnalysis();
    }

    private List<SongInfo> makeTestSongsList() {
        return List.of(
            new SongInfo(1L, Duration.ofMinutes(1L), "Test1", new Price("10", Currency.USD)),
            new SongInfo(2L, Duration.ofMinutes(1L), "Test1", new Price("20", Currency.USD)),
            new SongInfo(3L, Duration.ofMinutes(1L), "Test2", new Price("30", Currency.USD)),
            new SongInfo(4L, Duration.ofMinutes(1L), "Test3", new Price("40", Currency.USD)),
            new SongInfo(4L, Duration.ofMinutes(1L), "Test3", new Price("50", Currency.USD))
        );
    }

    private List<SongInfo> makeDifferentCurrenciesSongs() {
        return List.of(
            new SongInfo(1L, Duration.ofMinutes(1L), "Test1", new Price("10", Currency.USD)),
            new SongInfo(2L, Duration.ofMinutes(1L), "Test1", new Price("20", Currency.USD)),
            new SongInfo(3L, Duration.ofMinutes(1L), "Test2", new Price("30", Currency.PLN))
        );
    }

    @Test
    @DisplayName("Average song price should be 30.00")
    void calculateAveragePrice() {
        final List<SongInfo> songInfos = makeTestSongsList();
        final Price actualPrice = averageSongPriceAnalysis.calculateAveragePrice(songInfos);
        final Price expectedPrice = new Price("30.00", Currency.USD);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    @DisplayName("Should return null when called with an empty collection")
    void emptyCollectionAveragePrice() {
        final List<SongInfo> songInfos = Collections.emptyList();
        final Price actualPrice = averageSongPriceAnalysis.calculateAveragePrice(songInfos);

        assertNull(actualPrice);
    }

    @Test
    @DisplayName("Should throw DifferentCurrenciesException when currencies are different")
    void differentCurrenciesAveragePrice() {
        final List<SongInfo> songInfos = makeDifferentCurrenciesSongs();

        assertThrows(
            DifferentCurrenciesException.class,
            () -> averageSongPriceAnalysis.calculateAveragePrice(songInfos)
        );
    }
}