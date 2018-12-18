package com.jwsolutions.songanalyzer.songinfoproviders;

import java.util.LinkedList;
import java.util.List;

public class SongQueryBuilder {
    private List<String> queryStrings = new LinkedList<>();

    public SongQueryBuilder append(String searchPhrase) {
        queryStrings.add(searchPhrase);
        return this;
    }

    public SongQuery build() {
        return new SongQuery(queryStrings);
    }
}
