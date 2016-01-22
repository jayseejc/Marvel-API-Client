package com.jayseeofficial.marvel.rest.parameter;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 21/01/16.
 */
public class StoryParameters {

    private Date modifiedSince;
    private List<Integer> comics;
    private List<Integer> series;
    private List<Integer> events;
    private List<Integer> creators;
    private List<Integer> characters;
    private StoryOrderBy orderBy;
    private Integer limit;
    private Integer offset;

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getCreators() {
        return creators;
    }

    public List<Integer> getComics() {
        return comics;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public List<Integer> getCharacters() {
        return characters;
    }

    public StoryOrderBy getOrderBy() {
        return orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public static class Builder {
        private StoryParameters parameters = new StoryParameters();

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

        public Builder characters(List<Integer> characters){
            parameters.characters=characters;
            return this;
        }

        public Builder creators(List<Integer> creators) {
            parameters.creators = creators;
            return this;
        }

        public Builder orderBy(StoryOrderBy orderBy) {
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

        public StoryParameters build() {
            return parameters;
        }
    }

}
