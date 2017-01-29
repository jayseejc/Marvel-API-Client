package com.jayseeofficial.marvel.rest.model;

import java.io.Serializable;

public abstract class AbstractList implements Serializable {
    protected int available;
    protected int returned;
    protected String collectionURI;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }
}
