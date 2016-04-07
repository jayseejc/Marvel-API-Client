package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 21/01/16.
 */
public enum SeriesType {
    COLLECTION("collection"),
    ONE_SHOT("one shot"),
    LIMITED("limited"),
    ONGOING("ongoing");

    private final String string;

    private SeriesType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}