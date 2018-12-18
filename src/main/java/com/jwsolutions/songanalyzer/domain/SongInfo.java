package com.jwsolutions.songanalyzer.domain;

import com.jwsolutions.songanalyzer.domain.money.Price;

import java.time.Duration;
import java.util.Objects;

/**
 * Metadata of one song (e.g. duration, name, etc.).
 */
public class SongInfo {
    private final Object songId;
    private final Duration duration;
    private final String collectionName;
    private final Price trackPrice;

    public SongInfo(Object songId, Duration duration, String collectionName, Price trackPrice) {
        this.songId = songId;
        this.duration = duration;
        this.collectionName = collectionName;
        this.trackPrice = trackPrice;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Price getTrackPrice() {
        return trackPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SongInfo))
            return false;

        SongInfo songInfo = (SongInfo) o;
        return Objects.equals(songId, songInfo.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId);
    }
}
