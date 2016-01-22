package com.jayseeofficial.marvel.rest.parameter;

/**
 * Created by jon on 21/01/16.
 */
public enum CreatorOrderBy {
    LAST_NAME_ASC("lastName"),
    FIRST_NAME_ASC("firstName"),
    MIDDLE_NAME_ASC("middleName"),
    SUFFIX_ASC("suffix"),
    MODIFIED_ASC("modified"),
    LAST_NAME_DESC("-lastName"),
    FIRST_NAME_DESC("-firstName"),
    MIDDLE_NAME_DESC("-middleName"),
    SUFFIX_DESC("-suffix"),
    MODIFIED_DESC("-modified");

    private final String string;

    private CreatorOrderBy(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
