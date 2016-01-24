package com.jayseeofficial.marvel.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Image implements Serializable {
    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
