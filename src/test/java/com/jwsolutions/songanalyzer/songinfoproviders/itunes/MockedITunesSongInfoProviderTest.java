package com.jwsolutions.songanalyzer.songinfoproviders.itunes;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.Price;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockedITunesSongInfoProviderTest {

    private MockedITunesSongInfoProvider mockedITunesSongInfoProvider;

    @BeforeEach
    void setUp() {
        final URL testJsonFileURL = this.getClass().getResource("test_songs.json");
        mockedITunesSongInfoProvider = new MockedITunesSongInfoProvider(testJsonFileURL);
    }

    @Test
    @DisplayName("Should find \"In Between Dreams\" song")
    void find() throws IOException {
        SongQuery songQuery = mockSongQuery();

        final Collection<SongInfo> actualSongInfos = mockedITunesSongInfoProvider.find(songQuery);
        final Collection<SongInfo> expectedSongInfos = List.of(
            new SongInfo(
                new ITunesTrackId(879273565L),
                Duration.ofMillis(207679),
                "In Between Dreams (Bonus Track Version)",
                new Price("1.29", Currency.USD)
            )
        );

        assertEquals(expectedSongInfos, actualSongInfos);
    }

    @Test
    @DisplayName("Should throw an exception when queried with a non-mocked query")
    void testNonMockedSongQuery() {
        SongQuery unexpectedQuery = mockUnexpectedSongQuery();
        assertThrows(UnsupportedOperationException.class, () -> mockedITunesSongInfoProvider.find(unexpectedQuery));
    }

    private SongQuery mockSongQuery() {
        SongQuery query = mock(SongQuery.class);
        when(query.getQueryPhrases()).thenReturn(List.of("jack", "jackson"));
        return query;
    }

    private SongQuery mockUnexpectedSongQuery() {
        SongQuery query = mock(SongQuery.class);
        when(query.getQueryPhrases()).thenReturn(List.of("jack", "son"));
        return query;
    }
}