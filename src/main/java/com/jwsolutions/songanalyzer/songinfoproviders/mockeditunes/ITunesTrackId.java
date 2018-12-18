package com.jwsolutions.songanalyzer.songinfoproviders.mockeditunes;

import java.util.Objects;

/**
 * Class representing the identifier of a song withing the iTunes service.
 */
public final class ITunesTrackId {
    private final Long trackId;

    public ITunesTrackId(Long trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITunesTrackId that = (ITunesTrackId) o;
        return Objects.equals(trackId, that.trackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId);
    }
}
