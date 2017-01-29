package com.jayseeofficial.marvel.rest.parameter;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 20/01/16.
 */
public class CharacterParameters {

    protected List<Integer> series;
    protected List<Integer> events;
    protected List<Integer> stories;
    protected Integer limit;
    protected Integer offset;
    private String name;
    private String nameStartsWith;
    private Date modifiedSince;
    private List<Integer> comics;
    private CharacterOrderBy orderBy;
    private CharacterParameters() {
    }

    public List<Integer> getSeries() {
        return series;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public List<Integer> getStories() {
        return stories;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getName() {
        return name;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getComics() {
        return comics;
    }

    public CharacterOrderBy getOrderBy() {
        return orderBy;
    }

    public static class Builder {
        CharacterParameters parameters = new CharacterParameters();

        public Builder() {

        }

        public Builder(CharacterParameters baseParams) {
            parameters = baseParams;
        }

        public Builder name(String name) {
            parameters.name = name;
            return this;
        }

        public Builder nameStartsWith(String nameStartsWith) {
            parameters.nameStartsWith = nameStartsWith;
            return this;
        }

        public Builder modifiedSince(Date modifiedSince) {
            parameters.modifiedSince = modifiedSince;
            return this;
        }

        public Builder comics(List<Integer> comics) {
            parameters.comics = comics;
            return this;
        }

        public Builder series(List<Integer> series) {
            parameters.series = series;
            return this;
        }

        public Builder events(List<Integer> events) {
            parameters.events = events;
            return this;
        }

        public Builder stories(List<Integer> stories) {
            parameters.stories = stories;
            return this;
        }

        public Builder orderBy(CharacterOrderBy orderBy) {
            parameters.orderBy = orderBy;
            return this;
        }

        public Builder limit(Integer limit) {
            parameters.limit = limit;
            return this;
        }

        public Builder offset(Integer offset) {
            parameters.offset = offset;
            return this;
        }

        public CharacterParameters build() {
            return parameters;
        }

    }
}
