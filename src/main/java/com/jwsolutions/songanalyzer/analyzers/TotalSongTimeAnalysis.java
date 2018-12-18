package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;

import java.time.Duration;
import java.util.Collection;

/**
 * Analysis of the total duration of songs.
 */
public class TotalSongTimeAnalysis {

    public Duration calculateTotalDuration(Collection<SongInfo> songInfos) {
        return songInfos.stream()
                .map(SongInfo::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }
}
