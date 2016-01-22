package com.jayseeofficial.marvel.rest.parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jon on 20/01/16.
 */
public class CharacterParameters{

    private CharacterParameters() {
    }

    private String name;
    private String nameStartsWith;
    private Date modifiedSince;
    private List<Integer> comics;
    private CharacterOrderBy orderBy;
    protected List<Integer> series;
    protected List<Integer> events;
    protected List<Integer> stories;
    protected Integer limit;
    protected Integer offset;

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

        public Builder comics(Integer... comics) {
            parameters.comics = new ArrayList<>(comics.length);
            parameters.comics.addAll(Arrays.asList(comics));
            return this;
        }

        public Builder series(Integer... series) {
            parameters.series = new ArrayList<>(series.length);
            parameters.series.addAll(Arrays.asList(series));
            return this;
        }

        public Builder events(Integer... events) {
            parameters.events = new ArrayList<>(events.length);
            parameters.events.addAll(Arrays.asList(events));
            return this;
        }

        public Builder stories(Integer... stories) {
            parameters.stories = new ArrayList<>(stories.length);
            parameters.stories.addAll(Arrays.asList(stories));
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
