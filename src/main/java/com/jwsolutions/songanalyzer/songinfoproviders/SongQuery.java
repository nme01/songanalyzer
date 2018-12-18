package com.jwsolutions.songanalyzer.songinfoproviders;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Query for finding {@link com.jwsolutions.songanalyzer.domain.SongInfo}. To create a {@link SongQuery}
 * use {@link SongQueryBuilder}. The objects of that class are immutable.
 */
public class SongQuery {
    private List<String> queryPhrases;

    SongQuery(List<String> queryPhrases) {
        this.queryPhrases = Collections.unmodifiableList(queryPhrases);
    }

    public List<String> getQueryPhrases() {
        return queryPhrases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongQuery)) return false;
        SongQuery songQuery = (SongQuery) o;
        return Objects.equals(getQueryPhrases(), songQuery.getQueryPhrases());
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryPhrases);
    }

    @Override
    public String toString() {
        return "SongQuery{" +
                "queryPhrases=" + queryPhrases +
                '}';
    }
}
