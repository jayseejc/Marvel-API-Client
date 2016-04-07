package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Series;
import com.jayseeofficial.marvel.rest.model.Story;
import com.jayseeofficial.marvel.rest.parameter.CharacterParameters;
import com.jayseeofficial.marvel.rest.parameter.ComicParameters;
import com.jayseeofficial.marvel.rest.parameter.CreatorParameters;
import com.jayseeofficial.marvel.rest.parameter.EventParameters;
import com.jayseeofficial.marvel.rest.parameter.SeriesParameters;
import com.jayseeofficial.marvel.rest.parameter.StoryParameters;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by jon on 21/01/16.
 */
public class EventTest extends AbstractRestTest {

    private static final int ACTS_OF_VENGEANCE = 116;

    @Test
    public void getEvent() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Event>> future = SettableFuture.create();
        restClient.getEvent(ACTS_OF_VENGEANCE, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEvents() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Event>> future = SettableFuture.create();
        EventParameters parameters = new EventParameters.Builder().build();
        restClient.getEvents(parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEventCharacters() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        CharacterParameters parameters = new CharacterParameters.Builder().limit(1).build();
        restClient.getEventCharacters(ACTS_OF_VENGEANCE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEventComics() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().build();
        restClient.getEventComics(ACTS_OF_VENGEANCE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEventCreators() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Creator>> future = SettableFuture.create();
        CreatorParameters parameters = new CreatorParameters.Builder().build();
        restClient.getEventCreators(ACTS_OF_VENGEANCE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEventSeries() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Series>> future = SettableFuture.create();
        SeriesParameters parameters = new SeriesParameters.Builder().build();
        restClient.getEventSeries(ACTS_OF_VENGEANCE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getEventStories() throws InterruptedException, ExecutionException, TimeoutException {
        final SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().build();
        restClient.getEventStories(ACTS_OF_VENGEANCE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }
}
