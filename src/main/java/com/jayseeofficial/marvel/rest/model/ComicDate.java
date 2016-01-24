package com.jayseeofficial.marvel.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ComicDate implements Serializable {
    private String type;
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
