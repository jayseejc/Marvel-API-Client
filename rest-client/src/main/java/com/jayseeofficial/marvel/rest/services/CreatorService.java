package com.jayseeofficial.marvel.rest.services;

import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Series;
import com.jayseeofficial.marvel.rest.model.Story;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jon on 21/01/16.
 */
public interface CreatorService {

    @GET("/v1/public/creators")
    Call<Result<Creator>> getCreators(
            @Query("firstName") String firstName,
            @Query("middleName") String middleName,
            @Query("lastName") String lastName,
            @Query("suffix") String suffix,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("firstNameStartsWith") String firstNameStartsWith,
            @Query("middleNameStartsWith") String middleNameStartsWith,
            @Query("lastNameStartsWith") String lastNameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("events") String events,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/creators/{creatorId}")
    Call<Result<Creator>> getCreator(
            @Path("creatorId") int creatorId
    );

    @GET("/v1/public/creators/{creatorId}/comics")
    Call<Result<Comic>> getCreatorComics(
            @Path("creatorId") int creatorId,
            @Query("format") String format,
            @Query("formatType") String formatType,
            @Query("noVariants") Boolean noVariants,
            @Query("dateDescriptor") String dateDescriptor,
            @Query("dateRange") String dateRange,
            @Query("title") String title,
            @Query("titleStartsWith") String titleStartsWith,
            @Query("startYear") Integer startYear,
            @Query("issueNumber") Integer issueNumber,
            @Query("diamondCode") String diamondCode,
            @Query("digitalId") Integer digitalId,
            @Query("upc") String upc,
            @Query("isbn") String isbn,
            @Query("ean") String ean,
            @Query("issn") String issn,
            @Query("hasDigitalIssue") Boolean hasDigitalIssue,
            @Query("modifiedSince") Date modifiedSince,
            @Query("characters") String characters,
            @Query("series") String series,
            @Query("events") String events,
            @Query("stories") String stories,
            @Query("sharedAppearances") String sharedAppearances,
            @Query("collaborators") String collaborators,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/creators/{creatorId}/events")
    Call<Result<Event>> getCreatorEvents(
            @Path("creatorId") int creatorId,
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("characters") String characters,
            @Query("series") String series,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/creators/{creatorId}/series")
    Call<Result<Series>> getCreatorSeries(
            @Path("creatorId") int creatorId,
            @Query("title") String title,
            @Query("titleStartsWith") String titleStartsWith,
            @Query("startYear") Integer startYear,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("events") String events,
            @Query("characters") String characters,
            @Query("seriesType") String seriesType,
            @Query("contains") String contains,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/creators/{creatorId}/stories")
    Call<Result<Story>> getCreatorStories(
            @Path("creatorId") int creatorId,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("events") String events,
            @Query("characters") String characters,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
