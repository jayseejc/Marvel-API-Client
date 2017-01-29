package com.jayseeofficial.marvel.rest.parameter;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 21/01/16.
 */
public class SeriesParameters {

    private String title;
    private String titleStartsWith;
    private Integer startYear;
    private Date modifiedSince;
    private List<Integer> comics;
    private List<Integer> stories;
    private List<Integer> events;
    private List<Integer> creators;
    private List<Integer> characters;
    private SeriesType seriesType;
    private Format contains;
    private SeriesOrderBy orderBy;
    private Integer limit;
    private Integer offset;

    public String getTitle() {
        return title;
    }

    public String getTitleStartsWith() {
        return titleStartsWith;
    }

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getCreators() {
        return creators;
    }

    public List<Integer> getComics() {
        return comics;
    }

    public List<Integer> getStories() {
        return stories;
    }

    public SeriesOrderBy getOrderBy() {
        return orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public SeriesType getSeriesType() {
        return seriesType;
    }

    public Format getContains() {
        return contains;
    }

    public List<Integer> getCharacters() {
        return characters;
    }

    public static class Builder {
        private SeriesParameters parameters = new SeriesParameters();

        public Builder title(String name) {
            parameters.title = name;
            return this;
        }

        public Builder titleStartsWith(String nameStartsWith) {
            parameters.titleStartsWith = nameStartsWith;
            return this;
        }

        public Builder startYear(Integer startYear) {
            parameters.startYear = startYear;
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

        public Builder seriesType(SeriesType seriesType) {
            parameters.seriesType = seriesType;
            return this;
        }

        public Builder contains(Format contains) {
            parameters.contains = contains;
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

        public Builder events(List<Integer> events) {
            parameters.events = events;
            return this;
        }

        public Builder orderBy(SeriesOrderBy orderBy) {
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

        public Builder characters(List<Integer> characters) {
            parameters.characters = characters;
            return this;
        }

        public SeriesParameters build() {
            return parameters;
        }
    }

}
