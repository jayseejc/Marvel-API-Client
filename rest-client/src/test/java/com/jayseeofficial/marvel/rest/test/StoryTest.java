package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
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
public class StoryTest extends AbstractRestTest {

    private static final int STORY_ID = 8;

    @Test
    public void getStory() throws ExecutionException, InterruptedException, TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        restClient.getStory(STORY_ID, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStories() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().limit(1).build();
        restClient.getStories(parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStoryCharacters() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        CharacterParameters parameters = new CharacterParameters.Builder().limit(1).build();
        restClient.getStoryCharacters(STORY_ID, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStoryComics() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().limit(1).build();
        restClient.getStoryComics(STORY_ID, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStoryCreators() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        CreatorParameters parameters = new CreatorParameters.Builder().limit(1).build();
        restClient.getStoryCreators(STORY_ID, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStoryEvents() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<MarvelCharacter>> future = SettableFuture.create();
        EventParameters parameters = new EventParameters.Builder().limit(1).build();
        restClient.getStoryEvents(STORY_ID, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getStorySeries() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Series>> future = SettableFuture.create();
        SeriesParameters parameters = new SeriesParameters.Builder().limit(1).build();
        restClient.getStorySeries(STORY_ID, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }
}
