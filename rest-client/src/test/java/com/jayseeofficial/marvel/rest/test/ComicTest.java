package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Story;
import com.jayseeofficial.marvel.rest.parameter.CharacterParameters;
import com.jayseeofficial.marvel.rest.parameter.ComicParameters;
import com.jayseeofficial.marvel.rest.parameter.CreatorParameters;
import com.jayseeofficial.marvel.rest.parameter.EventParameters;
import com.jayseeofficial.marvel.rest.parameter.StoryParameters;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by jon on 21/01/16.
 */
public class ComicTest extends AbstractRestTest {

    private static final int RISE_OF_INCARNATES_16 = 55098;

    @Test
    public void getComic() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        restClient.getComic(RISE_OF_INCARNATES_16, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getComics() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().limit(1).build();
        restClient.getComics(parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getComicCharacters() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        CharacterParameters parameters = new CharacterParameters.Builder().limit(1).build();
        restClient.getComicCharacters(RISE_OF_INCARNATES_16, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getComicCreators() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        CreatorParameters parameters = new CreatorParameters.Builder().limit(1).build();
        restClient.getComicCreators(RISE_OF_INCARNATES_16, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT,TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getComicEvents() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Event>> future = SettableFuture.create();
        EventParameters parameters = new EventParameters.Builder().limit(1).build();
        restClient.getComicEvents(RISE_OF_INCARNATES_16, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT,TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getComicStories() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().limit(1).build();
        restClient.getComicStories(RISE_OF_INCARNATES_16, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT,TimeUnit.SECONDS).getData()).isNotNull();
    }
}
