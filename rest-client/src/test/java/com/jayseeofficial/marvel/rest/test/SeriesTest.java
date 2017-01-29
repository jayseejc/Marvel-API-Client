package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Series;
import com.jayseeofficial.marvel.rest.model.Story;
import com.jayseeofficial.marvel.rest.parameter.CharacterParameters;
import com.jayseeofficial.marvel.rest.parameter.ComicParameters;
import com.jayseeofficial.marvel.rest.parameter.CreatorParameters;
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
public class SeriesTest extends AbstractRestTest {
    protected static final int HUNDREDTH_ANNIVERSARY_SPECIAL = 18454;

    @Test
    public void getSeries() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Series>> future = SettableFuture.create();
        restClient.getSeries(HUNDREDTH_ANNIVERSARY_SPECIAL, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    // The plural of series is series. *sigh*
    @Test
    public void getSeriess() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Series>> future = SettableFuture.create();
        SeriesParameters parameters = new SeriesParameters.Builder().limit(1).build();
        restClient.getSeries(parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getSeriesCharacters() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Character>> future = SettableFuture.create();
        CharacterParameters parameters = new CharacterParameters.Builder().limit(1).build();
        restClient.getSeriesCharacters(HUNDREDTH_ANNIVERSARY_SPECIAL, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getSeriesComics() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Comic>> future = SettableFuture.create();
        ComicParameters parameters = new ComicParameters.Builder().limit(1).build();
        restClient.getSeriesComics(HUNDREDTH_ANNIVERSARY_SPECIAL, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getSeriesCreators() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Creator>> future = SettableFuture.create();
        CreatorParameters parameters = new CreatorParameters.Builder().limit(1).build();
        restClient.getSeriesCreators(HUNDREDTH_ANNIVERSARY_SPECIAL, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

    @Test
    public void getSeriesStories() throws InterruptedException, ExecutionException, TimeoutException {
        SettableFuture<Result<Story>> future = SettableFuture.create();
        StoryParameters parameters = new StoryParameters.Builder().limit(1).build();
        restClient.getSeriesStories(HUNDREDTH_ANNIVERSARY_SPECIAL, parameters, new TestSuccessCallback(future), new TestFailureCallback(future));
        assertThat(future.get(TIMEOUT, TimeUnit.SECONDS).getData()).isNotNull();
    }

}
