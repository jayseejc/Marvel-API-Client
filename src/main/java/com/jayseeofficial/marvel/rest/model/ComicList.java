package com.jayseeofficial.marvel.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class ComicList extends AbstractList implements Serializable {
    private List<ComicSummary> items;

    public List<ComicSummary> getItems() {
        return items;
    }

    public void setItems(List<ComicSummary> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
