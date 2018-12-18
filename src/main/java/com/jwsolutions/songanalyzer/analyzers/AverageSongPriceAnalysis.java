package com.jwsolutions.songanalyzer.analyzers;

import com.jwsolutions.songanalyzer.domain.SongInfo;
import com.jwsolutions.songanalyzer.domain.money.Price;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

/**
 * Analysis providing the average price of a song.
 */
public class AverageSongPriceAnalysis {

    public Price calculateAveragePrice(Collection<SongInfo> songInfos) {
        if (songInfos.isEmpty()) {
            return null;
        }

        Iterator<SongInfo> iterator = songInfos.iterator();

        Price sum = iterator.next().getTrackPrice();
        int numberOfSongs = 1;

        while (iterator.hasNext()) {
            Price price = iterator.next().getTrackPrice();
            sum = sum.add(price);
            ++numberOfSongs;
        }

        return sum.divide(new BigDecimal(numberOfSongs));
    }
}
