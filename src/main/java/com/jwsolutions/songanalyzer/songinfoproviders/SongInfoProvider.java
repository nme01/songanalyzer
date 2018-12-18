package com.jwsolutions.songanalyzer.songinfoproviders;

import com.jwsolutions.songanalyzer.domain.SongInfo;

import java.util.Collection;

/**
 * Provides metadata of songs ({@link SongInfo}) for a given {@link SongQuery}.
 */
public interface SongInfoProvider {
    Collection<SongInfo> find(SongQuery songQuery);
}
