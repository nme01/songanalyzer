package com.jwsolutions.songanalyzer.songinfoproviders.mockeditunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Data Transfer Object for the response received from iTunes service.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITunesResponse {
    private int resultCount;
    private List<ITunesSongInfo> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<ITunesSongInfo> getResults() {
        return results;
    }

    public void setResults(List<ITunesSongInfo> results) {
        this.results = results;
    }
}
