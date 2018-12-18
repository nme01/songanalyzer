package com.jwsolutions.songanalyzer.songinfoproviders.itunes;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.songinfoproviders.SongInfoProvider;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQuery;
import com.jwsolutions.songanalyzer.songinfoproviders.SongQueryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

/**
 * Mocks the behaviour of iTunes service. The only mocked request is the {@link SongQuery} for "jack jackson".
 */
public class MockedITunesSongInfoProvider implements SongInfoProvider {

    private static final SongQuery MOCKED_QUERY = new SongQueryBuilder()
        .append("jack")
        .append("jackson")
        .build();

    private final URL mockedDataUrl;
    private final ITunesJsonSongParser jsonSongParser;

    public MockedITunesSongInfoProvider(URL mockedDataUrl) {
        this.mockedDataUrl = mockedDataUrl;
        this.jsonSongParser = new ITunesJsonSongParser();
    }

    @Override
    public Collection<SongInfo> find(SongQuery songQuery) throws IOException {
        if (!MOCKED_QUERY.equals(songQuery)) {
            throw new UnsupportedOperationException(
                "Tried to use a SongQuery which is not mocked: " + songQuery.toString()
            );
        }

        final InputStream inputStream = this.mockedDataUrl.openStream();
        return jsonSongParser.parse(inputStream);
    }

}
