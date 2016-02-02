package com.jayseeofficial.marvel.rest.parameter;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 21/01/16.
 */
public class EventParameters {

    private EventParameters() {
    }

    private String name;
    private String nameStartsWith;
    private Date modifiedSince;
    private List<Integer> creators;
    private List<Integer> characters;
    private List<Integer> series;
    private List<Integer> comics;
    private List<Integer> stories;
    private EventOrderBy orderBy;
    private Integer limit;
    private Integer offset;

    public String getName() {
        return name;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getCreators() {
        return creators;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public List<Integer> getComics() {
        return comics;
    }

    public List<Integer> getStories() {
        return stories;
    }

    public EventOrderBy getOrderBy() {
        return orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public List<Integer> getCharacters() {
        return characters;
    }

    public static class Builder {
        private EventParameters parameters;

        public Builder(){
            parameters=new EventParameters();
        }

        public Builder(EventParameters baseParameters){
            parameters=baseParameters;
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

        public Builder creators(List<Integer> creators) {
            parameters.creators = creators;
            return this;
        }

        public Builder characters(List<Integer> characters){
            parameters.characters=characters;
            return this;
        }

        public Builder series(List<Integer> series) {
            parameters.series = series;
            return this;
        }

        public Builder comics(List<Integer> comics) {
            parameters.comics = comics;
            return this;
        }

        public Builder stories(List<Integer> stories) {
            parameters.stories = stories;
            return this;
        }

        public Builder orderBy(EventOrderBy orderBy) {
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

        public EventParameters build() {
            return parameters;
        }
    }
}
