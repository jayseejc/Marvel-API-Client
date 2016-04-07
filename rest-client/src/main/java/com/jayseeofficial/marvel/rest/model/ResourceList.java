package com.jayseeofficial.marvel.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class ResourceList implements Serializable {
    private int available;
    private int returned;
    private String collectionURI;
    private List items;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
