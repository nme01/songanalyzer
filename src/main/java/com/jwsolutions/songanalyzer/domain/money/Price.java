package com.jwsolutions.songanalyzer.domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Price which is a combination of the amount of money and the currency in which this amount is expressed. The class
 * is immutable.
 */
public class Price {
    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Price(String amount, Currency currency) {
        this(new BigDecimal(amount), currency);
    }

    public Price add(Price anotherPrice) {
        if (anotherPrice.currency != this.currency) {
            throw new UnsupportedOperationException("Currencies of prices being added must be the same");
        }

        BigDecimal summedAmount = this.amount.add(anotherPrice.amount);
        return new Price(summedAmount, this.currency);
    }

    /**
     * Returns a new {@link Price} which scale is set to a given value.
     *
     * @param newScale new scale to be set for the amount
     * @param roundingMode determines how the amount should be rounded
     * @return new {@link Price} which is rescaled
     *
     * @see BigDecimal#setScale(int, RoundingMode)
     */
    public Price setScale(int newScale, RoundingMode roundingMode) {
        BigDecimal scaledAmount = this.amount.setScale(newScale, roundingMode);
        return new Price(scaledAmount, this.currency);
    }

    @Override
    public String toString() {
        return this.amount.toString() + " " + this.currency.toString();
    }
}
