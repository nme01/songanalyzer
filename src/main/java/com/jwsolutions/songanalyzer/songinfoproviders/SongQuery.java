package com.jwsolutions.songanalyzer.songinfoproviders;

import java.util.Collections;
import java.util.List;

/**
 * Query for finding {@link com.jwsolutions.songanalyzer.domain.SongInfo}. To create a {@link SongQuery}
 * use {@link SongQueryBuilder}. The objects of that class are immutable and thus shouldn't be extended.
 */
public final class SongQuery {
    private List<String> queryPhrases;

    SongQuery(List<String> queryPhrases) {
        this.queryPhrases = Collections.unmodifiableList(queryPhrases);
    }

    List<String> getQueryPhrases() {
        return queryPhrases;
    }
}
