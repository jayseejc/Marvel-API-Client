package com.jayseeofficial.marvel.rest.parameter;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 21/01/16.
 */
public class CreatorParameters {
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String nameStartsWith;
    private String firstNameStartsWith;
    private String middleNameStartsWith;
    private String lastNameStartsWith;
    private Date modifiedSince;
    private List<Integer> comics;
    private List<Integer> series;
    private List<Integer> stories;
    private List<Integer> events;
    private CreatorOrderBy orderBy;
    private Integer limit;
    private Integer offset;

    private CreatorParameters(Builder builder) {
        firstName = builder.firstName;
        middleName = builder.middleName;
        lastName = builder.lastName;
        suffix = builder.suffix;
        nameStartsWith = builder.nameStartsWith;
        firstNameStartsWith = builder.firstNameStartsWith;
        middleNameStartsWith = builder.middleNameStartsWith;
        lastNameStartsWith = builder.lastNameStartsWith;
        modifiedSince = builder.modifiedSince;
        comics = builder.comics;
        series = builder.series;
        stories = builder.stories;
        orderBy = builder.orderBy;
        limit = builder.limit;
        offset = builder.offset;
        events = builder.events;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public String getFirstNameStartsWith() {
        return firstNameStartsWith;
    }

    public String getMiddleNameStartsWith() {
        return middleNameStartsWith;
    }

    public String getLastNameStartsWith() {
        return lastNameStartsWith;
    }

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getComics() {
        return comics;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public List<Integer> getStories() {
        return stories;
    }

    public CreatorOrderBy getOrderBy() {
        return orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public static final class Builder {
        private String firstName;
        private String middleName;
        private String lastName;
        private String suffix;
        private String nameStartsWith;
        private String firstNameStartsWith;
        private String middleNameStartsWith;
        private String lastNameStartsWith;
        private Date modifiedSince;
        private List<Integer> comics;
        private List<Integer> series;
        private List<Integer> stories;
        private CreatorOrderBy orderBy;
        private Integer limit;
        private Integer offset;
        private List<Integer> events;

        public Builder() {
        }

        public Builder(CreatorParameters copy) {
            this.firstName = copy.firstName;
            this.middleName = copy.middleName;
            this.lastName = copy.lastName;
            this.suffix = copy.suffix;
            this.nameStartsWith = copy.nameStartsWith;
            this.firstNameStartsWith = copy.firstNameStartsWith;
            this.middleNameStartsWith = copy.middleNameStartsWith;
            this.lastNameStartsWith = copy.lastNameStartsWith;
            this.modifiedSince = copy.modifiedSince;
            this.comics = copy.comics;
            this.series = copy.series;
            this.stories = copy.stories;
            this.orderBy = copy.orderBy;
            this.limit = copy.limit;
            this.offset = copy.offset;
            this.events = copy.events;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder middleName(String val) {
            middleName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder suffix(String val) {
            suffix = val;
            return this;
        }

        public Builder nameStartsWith(String val) {
            nameStartsWith = val;
            return this;
        }

        public Builder firstNameStartsWith(String val) {
            firstNameStartsWith = val;
            return this;
        }

        public Builder middleNameStartsWith(String val) {
            middleNameStartsWith = val;
            return this;
        }

        public Builder lastNameStartsWith(String val) {
            lastNameStartsWith = val;
            return this;
        }

        public Builder modifiedSince(Date val) {
            modifiedSince = val;
            return this;
        }

        public Builder comics(List<Integer> val) {
            comics = val;
            return this;
        }

        public Builder series(List<Integer> val) {
            series = val;
            return this;
        }

        public Builder stories(List<Integer> val) {
            stories = val;
            return this;
        }

        public Builder orderBy(CreatorOrderBy val) {
            orderBy = val;
            return this;
        }

        public Builder limit(Integer val) {
            limit = val;
            return this;
        }

        public Builder offset(Integer val) {
            offset = val;
            return this;
        }

        public Builder events(List<Integer> val) {
            events = val;
            return this;
        }

        public CreatorParameters build() {
            return new CreatorParameters(this);
        }
    }
}
