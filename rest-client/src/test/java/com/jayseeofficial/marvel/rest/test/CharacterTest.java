package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Series;
import com.jayseeofficial.marvel.rest.model.Story;
import com.jayseeofficial.marvel.rest.parameter.CharacterParameters;
import com.jayseeofficial.marvel.rest.parameter.ComicParameters;
import com.jayseeofficial.marvel.rest.parameter.EventParameters;
import com.jayseeofficial.marvel.rest.parameter.SeriesParameters;
import com.jayseeofficial.marvel.rest.parameter.StoryParameters;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by jon on 20/01/16.
 */
public class CharacterTest extends AbstractRestTest {

    protected static final int BLACK_WIDOW_ID = 1009189;

    @Test
    public void getCharacter() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        restClient.getCharacter(BLACK_WIDOW_ID, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCharacters() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        CharacterParameters parameters = new CharacterParameters.Builder().limit(1).build();
        restClient.getCharacters(parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCharacterComics() throws InterruptedException, ExecutionException,
            TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().limit(1).build();
        restClient.getCharacterComics(BLACK_WIDOW_ID, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCharacterEvents() throws InterruptedException, ExecutionException,
            TimeoutException {
        SettableFuture<Result<Event>> future = SettableFuture.create();
        EventParameters parameters = new EventParameters.Builder().limit(1).build();
        restClient.getCharacterEvents(BLACK_WIDOW_ID, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCharacterSeries() throws InterruptedException, ExecutionException,
            TimeoutException {
        SettableFuture<Result<Series>> future = SettableFuture.create();
        SeriesParameters parameters = new SeriesParameters.Builder().limit(1).build();
        restClient.getCharacterSeries(BLACK_WIDOW_ID, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCharacterStories() throws InterruptedException, ExecutionException,
            TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().limit(1).build();
        restClient.getCharacterStories(BLACK_WIDOW_ID, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }
}
