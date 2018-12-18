package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Analysis counting the number of distinct song collections.
 */
public class DistinctCollectionsAnalysis {

    public int countDistinctCollections(Collection<SongInfo> songInfos) {
        final Set<String> collectionsSet = songInfos.stream()
                .map(SongInfo::getCollectionName)
                .collect(Collectors.toSet());
        return collectionsSet.size();
    }
}
