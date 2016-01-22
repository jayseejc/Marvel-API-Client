package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Story;
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
 * Created by jon on 22/01/16.
 */
public class CreatorTest extends AbstractRestTest {

    private static final int STAN_LEE = 30;

    @Test
    public void getCreator() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        restClient.getCreator(STAN_LEE, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCreators() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        CreatorParameters parameters = new CreatorParameters.Builder().limit(1).build();
        restClient.getCreators(parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCreatorComics() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().limit(1).build();
        restClient.getCreatorComics(STAN_LEE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCreatorEvents() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Event>> future = SettableFuture.create();
        EventParameters parameters = new EventParameters.Builder().limit(1).build();
        restClient.getCreatorEvents(STAN_LEE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCreatorSeries() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        SeriesParameters parameters = new SeriesParameters.Builder().limit(1).build();
        restClient.getCreatorSeries(STAN_LEE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getCreatorStories() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().limit(1).build();
        restClient.getCreatorStories(STAN_LEE, parameters, new TestCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }
}
