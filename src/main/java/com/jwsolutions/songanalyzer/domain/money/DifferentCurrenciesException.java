package com.jwsolutions.songanalyzer.domain.money;

/**
 * Exception thrown when operation which requires {@link Price}s of the same currencies gets {@link Price}s
 * of different currencies.
 */
public class DifferentCurrenciesException extends RuntimeException {
    DifferentCurrenciesException(String message) {
        super(message);
    }
}
