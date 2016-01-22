package com.jayseeofficial.marvel.rest.services;

import com.jayseeofficial.marvel.rest.model.Comic;
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
public interface CharacterService {

    @GET("/v1/public/characters/{characterid}")
    Call<Result<MarvelCharacter>> getCharacter(@Path("characterid") int characterId);

    @GET("/v1/public/characters")
    Call<Result<MarvelCharacter>> getCharacters(
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("events") String events,
            @Query("stories") String stories,
            @Query("orderBy") String orderby,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/characters/{characterid}/comics")
    Call<Result<Comic>> getCharacterComics(
            @Path("characterid") int characterId,
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
            @Query("series") String series,
            @Query("events") String events,
            @Query("stories") String stories,
            @Query("sharedAppearances") String sharedAppearances,
            @Query("collaborators") String collaborators,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/characters/{characterid}/events")
    Call<Result<Event>> getCharacterEvents(
            @Path("characterid") int characterId,
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("creators") String creators,
            @Query("series") String series,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/characters/{characterid}/series")
    Call<Result<Series>> getCharacterSeries(
            @Path("characterid") int characterId,
            @Query("title") String title,
            @Query("titleStartsWith") String titleStartsWith,
            @Query("startYear") Integer startYear,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("stories") String stories,
            @Query("events") String events,
            @Query("creators") String creators,
            @Query("seriesType") String seriesType,
            @Query("contains") String contains,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    @GET("/v1/public/characters/{characterid}/stories")
    Call<Result<Story>> getCharacterStories(
            @Path("characterid") int characterid,
            @Query("modifiedSince") Date modifiedSince,
            @Query("comics") String comics,
            @Query("series") String series,
            @Query("events") String events,
            @Query("creators") String creators,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
