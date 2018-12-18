package com.jwsolutions.songanalyzer.songinfoproviders.mockeditunes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Currency;
import com.jwsolutions.songanalyzer.domain.money.Price;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser for the responses got from iTunes service.
 */
class ITunesJsonSongParser {

    /**
     * Parses the response got from the iTunes service.
     *
     * @param inputStream stream of input data (can come from file, HTTP connection etc.)
     * @return collection of song metadata
     * @throws IOException if error occurred during reading data from the input data stream
     */
    public Collection<SongInfo> parse(InputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final ITunesResponse response = mapper.readValue(inputStream, ITunesResponse.class);
        final List<ITunesSongInfo> results = response.getResults();

        return results.stream()
                .map(ITunesJsonSongParser::parseSongInfo)
                .collect(Collectors.toList());
    }

    private static SongInfo parseSongInfo(ITunesSongInfo iTunesSongInfo) {
        Object songId = new ITunesTrackId(iTunesSongInfo.getTrackId());
        Duration duration = Duration.ofMillis(iTunesSongInfo.getTrackTimeMillis());
        String collectionName = iTunesSongInfo.getCollectionName();
        Price trackPrice = parsePrice(iTunesSongInfo.getTrackPrice(), iTunesSongInfo.getCurrency());

        return new SongInfo(songId, duration, collectionName, trackPrice);
    }

    private static Price parsePrice(BigDecimal trackPriceAmount, String currencyStr) {
        Currency currency = Currency.valueOf(currencyStr.toUpperCase());
        return new Price(trackPriceAmount, currency);
    }
}
