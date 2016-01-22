package com.jayseeofficial.marvel.rest.services;

import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
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
public interface EventService {

    @GET("/v1/public/events/{eventid}")
    Call<Result<Event>> getEvent(
            @Path("eventid") int eventId
    );

    @GET("/v1/public/events")
    Call<Result<Event>> getEvents(
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("creators") String creators,
            @Query("characters") String characters,
            @Query("series") String series,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/events/{eventId}/characters")
    Call<Result<MarvelCharacter>> getEventCharacters(
            @Path("eventId") int eventId,
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/events/{eventId}/comics")
    Call<Result<Comic>> getEventComics(
            @Path("eventId") int eventId,
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
            @Query("creators") String creators,
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

    @GET("/v1/public/events/{eventId}/creators")
    Call<Result<Creator>> getEventCreators(
            @Path("eventId") int eventId,
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
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/events/{eventId}/series")
    Call<Result<Series>> getEventSeries(
            @Path("eventId") int eventId,
            @Query("title") String title,
            @Query("titleStartsWith") String titleStartsWith,
            @Query("startYear") Integer startYear,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("creators") String creators,
            @Query("characters") String characters,
            @Query("seriesType") String seriesType,
            @Query("contains") String contains,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/events/{eventId}/stories")
    Call<Result<Story>> getEventStories(
            @Path("eventId") int eventId,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("creators") String creators,
            @Query("characters") String characters,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
