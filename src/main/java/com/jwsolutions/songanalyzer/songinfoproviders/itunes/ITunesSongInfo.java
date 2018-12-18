package com.jwsolutions.songanalyzer.songinfoproviders.itunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwsolutions.songanalyzer.domain.SongInfo;

import java.math.BigDecimal;

/**
 * Data Transfer Object for storing part of the iTunes response which corresponds to the {@link SongInfo}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITunesSongInfo {
    private Long trackId;
    private Long trackTimeMillis;
    private String collectionName;
    private BigDecimal trackPrice;
    private String currency;

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Long trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public BigDecimal getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(BigDecimal trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
