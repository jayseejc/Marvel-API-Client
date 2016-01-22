package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 20/01/16.
 */
public enum CharacterOrderBy {
    NAME_ASC("name"),
    MODIFIED_ASC("modified"),
    NAME_DESC("-name"),
    MODIFIED_DESC("-modified");

    private final String string;

    private CharacterOrderBy(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
