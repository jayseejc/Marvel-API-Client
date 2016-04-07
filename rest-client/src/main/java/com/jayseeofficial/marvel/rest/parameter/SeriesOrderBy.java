package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 21/01/16.
 */

public enum SeriesOrderBy {

    TITLE_ASC("title"),
    MODIFIED_ASC("modified"),
    START_YEAR_ASC("startYear"),
    TITLE_DESC("-title"),
    MODIFIED_DESC("-modified"),
    START_YEAR_DESC("-startYear");

    private final String string;

    private SeriesOrderBy(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}