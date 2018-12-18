package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistinctCollectionsAnalysisTest {

    private DistinctCollectionsAnalysis distinctCollectionsAnalysis;

    private List<SongInfo> makeTestSongsList() {
        return List.of(
            new SongInfo(1L, Duration.ofMinutes(1L), "Test1", new Price("10", Currency.USD)),
            new SongInfo(2L, Duration.ofMinutes(1L), "Test1", new Price("20", Currency.USD)),
            new SongInfo(3L, Duration.ofMinutes(1L), "Test2", new Price("30", Currency.USD)),
            new SongInfo(4L, Duration.ofMinutes(1L), "Test3", new Price("40", Currency.USD)),
            new SongInfo(4L, Duration.ofMinutes(1L), "Test3", new Price("50", Currency.USD))
        );
    }

    @BeforeEach
    void setUp() {
        distinctCollectionsAnalysis = new DistinctCollectionsAnalysis();
    }

    @Test
    @DisplayName("Should find 3 distinct collections")
    void countDistinctCollections() {
        Collection<SongInfo> songInfos = makeTestSongsList();
        final int actualDistinctCollections = distinctCollectionsAnalysis.countDistinctCollections(songInfos);
        assertEquals(3, actualDistinctCollections);
    }

    @Test
    @DisplayName("Should return 0 if called with empty collection")
    void testEmptyCollection() {
        Collection<SongInfo> songInfos = Collections.emptyList();
        final int actualDistinctCollections = distinctCollectionsAnalysis.countDistinctCollections(songInfos);
        assertEquals(0, actualDistinctCollections);
    }
}