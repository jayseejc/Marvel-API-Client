package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 21/01/16.
 */
public enum StoryOrderBy {
    ID_ASC("id"),
    MODIFIED_ASC("modified"),
    ID_DESC("-id"),
    MODIFIED_DESC("-modified");

    private final String string;

    private StoryOrderBy(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
