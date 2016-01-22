package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 20/01/16.
 */
public enum DateDescriptor {
    LAST_WEEK("lastWeek"), THIS_WEEK("thisWeek"), NEXT_WEEK("nextWeek"), THIS_MONTH("thisMonth");

    private final String string;

    private DateDescriptor(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
