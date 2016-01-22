package com.jayseeofficial.marvel.rest.parameter;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

/**
 * Created by jon on 20/01/16.
 */
public class ComicParameters {

    private Format format;
    private FormatType formatType;
    private Boolean noVariants;
    private DateDescriptor dateDescriptor;
    private Pair<String, String> dateRange;
    private String title;
    private String titleStartsWith;
    private Integer startYear;
    private Integer issueNumber;
    private String diamondCode;
    private Integer digitalId;
    private String upc;
    private String isbn;
    private String ean;
    private String issn;
    private Boolean hasDigitalIssue;
    private Date modifiedSince;
    private List<Integer> creators;
    private List<Integer> characters;
    private List<Integer> series;
    private List<Integer> events;
    private List<Integer> stories;
    private List<Integer> sharedAppearances;
    private List<Integer> collaborators;
    private ComicOrderBy orderBy;
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

    private ComicParameters() {
    }

    public Format getFormat() {
        return format;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public Boolean getNoVariants() {
        return noVariants;
    }

    public DateDescriptor getDateDescriptor() {
        return dateDescriptor;
    }

    public Pair<String, String> getDateRange() {
        return dateRange;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleStartsWith() {
        return titleStartsWith;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public Integer getDigitalId() {
        return digitalId;
    }

    public String getUpc() {
        return upc;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEan() {
        return ean;
    }

    public String getIssn() {
        return issn;
    }

    public Boolean getHasDigitalIssue() {
        return hasDigitalIssue;
    }

    public Date getModifiedSince() {
        return modifiedSince;
    }

    public List<Integer> getCreators() {
        return creators;
    }

    public List<Integer> getSharedAppearances() {
        return sharedAppearances;
    }

    public List<Integer> getCollaborators() {
        return collaborators;
    }

    public ComicOrderBy getOrderBy() {
        return orderBy;
    }

    public List<Integer> getCharacters() {
        return characters;
    }

    public static class Builder {
        ComicParameters parameters = new ComicParameters();

        public Builder format(Format format) {
            parameters.format = format;
            return this;
        }

        public Builder formatType(FormatType formatType) {
            parameters.formatType = formatType;
            return this;
        }

        public Builder noVarients(Boolean noVarients) {
            parameters.noVariants = noVarients;
            return this;
        }

        public Builder dateDescriptor(DateDescriptor dateDescriptor) {
            parameters.dateDescriptor = dateDescriptor;
            return this;
        }

        public Builder dateRange(final String date1, final String date2) {
            parameters.dateRange = new Pair<String, String>() {
                @Override
                public String getLeft() {
                    return date1;
                }

                @Override
                public String getRight() {
                    return date2;
                }

                @Override
                public String setValue(String value) {
                    return null;
                }
            };
            return this;
        }

        public Builder title(String title) {
            parameters.title = title;
            return this;
        }

        public Builder titleStartsWith(String titleStartsWith) {
            parameters.titleStartsWith = titleStartsWith;
            return this;
        }

        public Builder startYear(Integer startYear) {
            parameters.startYear = startYear;
            return this;
        }

        public Builder issueNumber(Integer issueNumber) {
            parameters.issueNumber = issueNumber;
            return this;
        }

        public Builder diamondCode(String diamondCode) {
            parameters.diamondCode = diamondCode;
            return this;
        }

        public Builder digitalId(Integer digitalId){
            parameters.digitalId=digitalId;
            return this;
        }

        public Builder upc(String upc){
            parameters.upc=upc;
            return this;
        }

        public Builder isbn(String isbn){
            parameters.isbn=isbn;
            return this;
        }

        public Builder ean(String ean){
            parameters.ean=ean;
            return this;
        }

        public Builder issn(String issn){
            parameters.issn=issn;
            return this;
        }

        public Builder hasDigitalIssue(Boolean hasDigitalIssue){
            parameters.hasDigitalIssue=hasDigitalIssue;
            return this;
        }

        public Builder modifiedSince(Date modifiedSince){
            parameters.modifiedSince=modifiedSince;
            return this;
        }

        public Builder creators(List<Integer> creators){
            parameters.creators=creators;
            return this;
        }

        public Builder characters(List<Integer> characters){
            parameters.characters=characters;
            return this;
        }

        public Builder series(List<Integer> series){
            parameters.series=series;
            return this;
        }

        public Builder events(List<Integer> events){
            parameters.events=events;
            return this;
        }

        public Builder stories(List<Integer> stories){
            parameters.stories=stories;
            return this;
        }

        public Builder sharedApperences(List<Integer> sharedAppearances) {
            parameters.sharedAppearances = sharedAppearances;
            return this;
        }

        public Builder collaberators(List<Integer> collaborators){
            parameters.collaborators =collaborators;
            return this;
        }

        public Builder orderBy(ComicOrderBy orderBy){
            parameters.orderBy=orderBy;
            return this;
        }

        public Builder limit(Integer limit){
            parameters.limit=limit;
            return this;
        }

        public ComicParameters build(){
            return parameters;
        }
    }

}

