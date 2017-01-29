package com.jayseeofficial.marvel.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Image implements Serializable {

    public static final String NOT_FOUND_PATH =
            "https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available";
    public static final String NOT_FOUND_EXTENTION = "jpg";

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
