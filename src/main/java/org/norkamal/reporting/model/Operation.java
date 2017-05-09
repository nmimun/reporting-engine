package org.norkamal.reporting.model;

/**
 * The Operation class represents the list of valid operation types.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public enum Operation {
    BUY("outgoing"),
    SELL("incoming");

    private final String value;

    Operation(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
