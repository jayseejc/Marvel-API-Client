package com.jayseeofficial.marvel.rest.services;

import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Story;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jon on 21/01/16.
 */
public interface ComicService {

    //<editor-fold desc="getComic()">
    @GET("/v1/public/comics/{comicid}")
    Call<Result<Comic>> getComic(@Path("comicid") int comicId);
    //</editor-fold>

    //<editor-fold desc="getComics()">
    @GET("/v1/public/comics")
    Call<Result<Comic>> getComics(
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
    //</editor-fold>

    //<editor-fold desc="getComicCharacters()">
    @GET("/v1/public/comics/{comicid}/characters")
    Call<Result<MarvelCharacter>> getComicCharacters(
            @Path("comicid") int comicId,
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("series") String series,
            @Query("events") String events,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
    //</editor-fold>

    //<editor-fold desc="getComicCreators()">
    @GET("/v1/public/comics/{comicid}/creators")
    Call<Result<Creator>> getComicCreators(
            @Path("comicid") int comicId,
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
    //</editor-fold>

    //<editor-fold desc="getComicEvents()">
    @GET("/v1/public/comics/{comicid}/events")
    Call<Result<Event>> getComicEvents(
            @Path("comicid") int comicId,
            @Query("name") String name,
            @Query("nameStartsWith") String nameStartsWith,
            @Query("modifiedSince") Date modifiedSince,
            @Query("creators") String creators,
            @Query("characters") String characters,
            @Query("series") String series,
            @Query("stories") String stories,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
    //</editor-fold>

    //<editor-fold desc="getComicStories()">
    @GET("/v1/public/comics/{comicid}/stories")
    Call<Result<Story>> getComicStories(
            @Path("comicid") int comicId,
            @Query("modifiedSince") Date modifiedSince,
            @Query("series") String series,
            @Query("events") String events,
            @Query("creators") String creators,
            @Query("characters") String characters,
            @Query("orderBy") String orderBy,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
    //</editor-fold>
}
