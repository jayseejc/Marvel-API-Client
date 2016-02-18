package com.jayseeofficial.marvel.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayseeofficial.marvel.rest.interceptor.AuthenticationInterceptor;
import com.jayseeofficial.marvel.rest.model.Comic;
import com.jayseeofficial.marvel.rest.model.Creator;
import com.jayseeofficial.marvel.rest.model.Event;
import com.jayseeofficial.marvel.rest.model.MarvelCharacter;
import com.jayseeofficial.marvel.rest.model.Result;
import com.jayseeofficial.marvel.rest.model.Series;
import com.jayseeofficial.marvel.rest.model.Story;
import com.jayseeofficial.marvel.rest.parameter.CharacterOrderBy;
import com.jayseeofficial.marvel.rest.parameter.CharacterParameters;
import com.jayseeofficial.marvel.rest.parameter.ComicParameters;
import com.jayseeofficial.marvel.rest.parameter.CreatorParameters;
import com.jayseeofficial.marvel.rest.parameter.EventParameters;
import com.jayseeofficial.marvel.rest.parameter.SeriesParameters;
import com.jayseeofficial.marvel.rest.parameter.StoryParameters;
import com.jayseeofficial.marvel.rest.services.CharacterService;
import com.jayseeofficial.marvel.rest.services.ComicService;
import com.jayseeofficial.marvel.rest.services.CreatorService;
import com.jayseeofficial.marvel.rest.services.EventService;
import com.jayseeofficial.marvel.rest.services.SeriesService;
import com.jayseeofficial.marvel.rest.services.StoryService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jon on 20/01/16.
 */
public class RestClient {

    public static final int MAX_LIMIT = 100;

    private static final String MARVEL_BASE_URL = "https://gateway.marvel.com";
    private static final long DEFAULT_HTTP_TIMEOUT = 30; // Seconds
    private static final HttpLoggingInterceptor.Level DEFAULT_LOG_LEVEL = HttpLoggingInterceptor.Level.NONE;

    private String publicKey;
    private String privateKey;
    private long timeout;
    private HttpLoggingInterceptor.Level logLevel;
    private HttpLoggingInterceptor loggingInterceptor;
    private AuthenticationInterceptor authenticationInterceptor;
    private OkHttpClient client;
    private List<Call<?>> calls;

    private Retrofit retrofit;
    private CharacterService characters;
    private ComicService comics;
    private CreatorService creators;
    private EventService events;
    private StoryService stories;
    private SeriesService series;
    private Cache cache = null;

    public RestClient(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.timeout = DEFAULT_HTTP_TIMEOUT;
        this.logLevel = DEFAULT_LOG_LEVEL;
        this.calls = new ArrayList<>();

        initLogging();
        initAuth();
        initHttpClient();
        initServices();
    }

    private void initLogging() {
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(logLevel);
        initHttpClient();
    }

    private void initAuth() {
        authenticationInterceptor = new AuthenticationInterceptor(publicKey, privateKey, null);
    }

    private void initHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new com.jayseeofficial.marvel.rest.interceptor.UserAgentInterceptor());
        if (cache != null) {
            builder.cache(cache);
            builder.addInterceptor(new AuthenticationInterceptor(publicKey, privateKey, new File(cache.directory().getAbsolutePath() + "/../auth")));
        } else {
            builder.addInterceptor(new AuthenticationInterceptor(publicKey, privateKey, null));
        }
        client = builder.build();
        initServices();
    }

    private void initServices() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MARVEL_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        characters = retrofit.create(CharacterService.class);
        comics = retrofit.create(ComicService.class);
        events = retrofit.create(EventService.class);
        stories = retrofit.create(StoryService.class);
        series = retrofit.create(SeriesService.class);
        creators = retrofit.create(CreatorService.class);
    }

    // <editor-fold desc="Character methods">

    public void getCharacter(int id, final Callback<MarvelCharacter> callback) {
        final Call<Result<MarvelCharacter>> call = characters.getCharacter(id);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(retrofit2.Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCharacters(CharacterParameters parameters, final Callback<MarvelCharacter> callback) {
        if (parameters == null) parameters = new CharacterParameters.Builder().build();
        CharacterOrderBy orderBy = parameters.getOrderBy();

        String orderByString = null;
        if (orderBy != null)
            orderByString = orderBy.toString();

        final Call<Result<MarvelCharacter>> call = characters.getCharacters(
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCharacterComics(Integer characterId, ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = characters.getCharacterComics(
                characterId,
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCharacterEvents(Integer characterId, EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null) orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = characters.getCharacterEvents(
                characterId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getSeries()),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCharacterSeries(Integer characterId, SeriesParameters parameters, final Callback<Series> callback) {
        if (parameters == null) parameters = new SeriesParameters.Builder().build();

        String seriesTypeString = null;
        if (parameters.getSeriesType() != null)
            seriesTypeString = parameters.getSeriesType().toString();

        String containsString = null;
        if (parameters.getContains() != null)
            containsString = parameters.getContains().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Series>> call = characters.getCharacterSeries(
                characterId,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                seriesTypeString,
                containsString,
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCharacterStories(Integer characterId, StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = characters.getCharacterStories(
                characterId,
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    //</editor-fold>

    //<editor-fold desc="Comic methods">

    public void getComic(Integer comicId, final Callback<Comic> callback) {
        final Call<Result<Comic>> call = comics.getComic(comicId);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getComics(ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = comics.getComics(
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getComicCharacters(Integer comicId, CharacterParameters parameters, final Callback<MarvelCharacter> callback) {
        if (parameters == null) parameters = new CharacterParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<MarvelCharacter>> call = comics.getComicCharacters(
                comicId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getComicCreators(Integer comicId, CreatorParameters parameters, final Callback<Creator> callback) {
        if (parameters == null) parameters = new CreatorParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Creator>> call = comics.getComicCreators(
                comicId,
                parameters.getFirstName(),
                parameters.getMiddleName(),
                parameters.getLastName(),
                parameters.getSuffix(),
                parameters.getNameStartsWith(),
                parameters.getFirstNameStartsWith(),
                parameters.getMiddleNameStartsWith(),
                parameters.getLastNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getComicEvents(Integer comicId, EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = comics.getComicEvents(
                comicId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });

    }

    public void getComicStories(Integer comicId, StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = comics.getComicStories(
                comicId,
                parameters.getModifiedSince(),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                orderByString, parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    //</editor-fold>

    //<editor-fold desc="Creator methods">
    public void getCreator(Integer creatorId, final Callback<Creator> callback) {
        final Call<Result<Creator>> call = creators.getCreator(creatorId);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCreators(CreatorParameters parameters, final Callback<Creator> callback) {
        if (parameters == null) parameters = new CreatorParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Creator>> call = creators.getCreators(
                parameters.getFirstName(),
                parameters.getMiddleName(),
                parameters.getLastName(),
                parameters.getSuffix(),
                parameters.getNameStartsWith(),
                parameters.getFirstNameStartsWith(),
                parameters.getMiddleNameStartsWith(),
                parameters.getLastNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCreatorComics(Integer creatorId, ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = creators.getCreatorComics(
                creatorId,
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCreatorEvents(Integer creatorId, EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = creators.getCreatorEvents(
                creatorId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCreatorSeries(Integer creatorId, SeriesParameters parameters, final Callback<Series> callback) {
        if (parameters == null) parameters = new SeriesParameters.Builder().build();

        String seriesTypeString = null;
        if (parameters.getSeriesType() != null)
            seriesTypeString = parameters.getSeriesType().toString();

        String containsString = null;
        if (parameters.getContains() != null)
            containsString = parameters.getContains().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Series>> call = creators.getCreatorSeries(
                creatorId,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCharacters()),
                seriesTypeString,
                containsString,
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getCreatorStories(Integer creatorId, StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = creators.getCreatorStories(
                creatorId,
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCharacters()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc="Event methods">
    public void getEvent(Integer eventId, final Callback<Event> callback) {
        final Call<Result<Event>> call = events.getEvent(eventId);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEvents(EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = events.getEvents(
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEventCharacters(Integer eventId, CharacterParameters parameters, final Callback<MarvelCharacter> callback) {
        if (parameters == null) parameters = new CharacterParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<MarvelCharacter>> call = events.getEventCharacters(
                eventId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEventComics(Integer eventId, ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = events.getEventComics(
                eventId,
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEventCreators(Integer eventId, CreatorParameters parameters, final Callback<Creator> callback) {
        if (parameters == null) parameters = new CreatorParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Creator>> call = events.getEventCreators(
                eventId,
                parameters.getFirstName(),
                parameters.getMiddleName(),
                parameters.getLastName(),
                parameters.getSuffix(),
                parameters.getNameStartsWith(),
                parameters.getFirstNameStartsWith(),
                parameters.getMiddleNameStartsWith(),
                parameters.getLastNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEventSeries(Integer eventId, SeriesParameters parameters, final Callback<Series> callback) {
        if (parameters == null) parameters = new SeriesParameters.Builder().build();

        String seriesTypeString = null;
        if (parameters.getSeriesType() != null)
            seriesTypeString = parameters.getSeriesType().toString();

        String containsString = null;
        if (parameters.getContains() != null)
            containsString = parameters.getContains().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Series>> call = events.getEventSeries(
                eventId,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                seriesTypeString,
                containsString,
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.remove(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getEventStories(Integer eventId, StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = events.getEventStories(
                eventId,
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    //</editor-fold>

    //<editor-fold desc="Series methods">

    public void getSeries(Integer seriesId, final Callback<Series> callback) {
        final Call<Result<Series>> call = series.getSeries(seriesId);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeries(SeriesParameters parameters, final Callback<Series> callback) {
        if (parameters == null) parameters = new SeriesParameters.Builder().build();

        String seriesTypeString = null;
        if (parameters.getSeriesType() != null)
            seriesTypeString = parameters.getSeriesType().toString();

        String containsString = null;
        if (parameters.getContains() != null)
            containsString = parameters.getContains().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Series>> call = series.getSeries(
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                seriesTypeString,
                containsString,
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeriesCharacters(Integer seriesId, CharacterParameters parameters, final Callback<MarvelCharacter> callback) {
        if (parameters == null) parameters = new CharacterParameters.Builder().build();
        CharacterOrderBy orderBy = parameters.getOrderBy();
        String orderByString = null;
        if (orderBy != null)
            orderByString = orderBy.toString();

        final Call<Result<MarvelCharacter>> call = series.getSeriesCharacters(
                seriesId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeriesComics(Integer seriesId, ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = series.getSeriesComics(
                seriesId,
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeriesCreators(Integer seriesId, CreatorParameters parameters, final Callback<Creator> callback) {
        if (parameters == null) parameters = new CreatorParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Creator>> call = series.getSeriesCreators(
                seriesId,
                parameters.getFirstName(),
                parameters.getMiddleName(),
                parameters.getLastName(),
                parameters.getSuffix(),
                parameters.getNameStartsWith(),
                parameters.getFirstNameStartsWith(),
                parameters.getMiddleNameStartsWith(),
                parameters.getLastNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getEvents()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeriesEvents(Integer seriesId, EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = series.getSeriesEvents(
                seriesId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getComics()),
                listToString(parameters.getStories()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getSeriesStories(Integer seriesId, StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = series.getSeriesStories(
                seriesId,
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    //</editor-fold>

    //<editor-fold desc="Story methods">
    public void getStory(Integer storyId, final Callback<Story> callback) {
        final Call<Result<Story>> call = stories.getStory(storyId);
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStories(StoryParameters parameters, final Callback<Story> callback) {
        if (parameters == null) parameters = new StoryParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Story>> call = stories.getStories(
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Story>>() {
            @Override
            public void onResponse(Response<Result<Story>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStoryCharacters(Integer storyId, CharacterParameters parameters, final Callback<MarvelCharacter> callback) {
        if (parameters == null) parameters = new CharacterParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<MarvelCharacter>> call = stories.getStoryCharacters(
                storyId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<MarvelCharacter>>() {
            @Override
            public void onResponse(Response<Result<MarvelCharacter>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStoryComics(Integer storyId, ComicParameters parameters, final Callback<Comic> callback) {
        if (parameters == null) parameters = new ComicParameters.Builder().build();

        String formatString = null;
        if (parameters.getFormat() != null)
            formatString = parameters.getFormat().toString();

        String formatTypeString = null;
        if (parameters.getFormatType() != null)
            formatTypeString = parameters.getFormatType().toString();

        String dateDescriptorString = null;
        if (parameters.getDateDescriptor() != null)
            dateDescriptorString = parameters.getDateDescriptor().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        String dateRangeString = null;
        if (parameters.getDateRange() != null)
            dateRangeString = parameters.getDateRange().getLeft() + "," + parameters.getDateRange().getRight();

        final Call<Result<Comic>> call = stories.getStoryComics(
                storyId,
                formatString,
                formatTypeString,
                parameters.getNoVariants(),
                dateDescriptorString,
                dateRangeString,
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getIssueNumber(),
                parameters.getDiamondCode(),
                parameters.getDigitalId(),
                parameters.getUpc(),
                parameters.getIsbn(),
                parameters.getEan(),
                parameters.getIssn(),
                parameters.getHasDigitalIssue(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                listToString(parameters.getSharedAppearances()),
                listToString(parameters.getCollaborators()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Comic>>() {
            @Override
            public void onResponse(Response<Result<Comic>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStoryCreators(Integer storyId, CreatorParameters parameters, final Callback<Creator> callback) {
        if (parameters == null) parameters = new CreatorParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Creator>> call = stories.getStoryCreators(
                storyId,
                parameters.getFirstName(),
                parameters.getMiddleName(),
                parameters.getLastName(),
                parameters.getSuffix(),
                parameters.getNameStartsWith(),
                parameters.getFirstNameStartsWith(),
                parameters.getMiddleNameStartsWith(),
                parameters.getLastNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getSeries()),
                listToString(parameters.getEvents()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.remove(call);
        call.enqueue(new retrofit2.Callback<Result<Creator>>() {
            @Override
            public void onResponse(Response<Result<Creator>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStoryEvents(Integer storyId, EventParameters parameters, final Callback<Event> callback) {
        if (parameters == null) parameters = new EventParameters.Builder().build();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Event>> call = stories.getStoryEvents(
                storyId,
                parameters.getName(),
                parameters.getNameStartsWith(),
                parameters.getModifiedSince(),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                listToString(parameters.getSeries()),
                listToString(parameters.getComics()),
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Event>>() {
            @Override
            public void onResponse(Response<Result<Event>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    public void getStorySeries(Integer storyId, SeriesParameters parameters, final Callback<Series> callback) {
        if (parameters == null) parameters = new SeriesParameters.Builder().build();

        String seriesTypeString = null;
        if (parameters.getSeriesType() != null)
            seriesTypeString = parameters.getSeriesType().toString();

        String containsString = null;
        if (parameters.getContains() != null)
            containsString = parameters.getContains().toString();

        String orderByString = null;
        if (parameters.getOrderBy() != null)
            orderByString = parameters.getOrderBy().toString();

        final Call<Result<Series>> call = stories.getStorySeries(
                storyId,
                listToString(parameters.getEvents()),
                parameters.getTitle(),
                parameters.getTitleStartsWith(),
                parameters.getStartYear(),
                parameters.getModifiedSince(),
                listToString(parameters.getComics()),
                listToString(parameters.getCreators()),
                listToString(parameters.getCharacters()),
                seriesTypeString,
                containsString,
                orderByString,
                parameters.getLimit(),
                parameters.getOffset());
        calls.add(call);
        call.enqueue(new retrofit2.Callback<Result<Series>>() {
            @Override
            public void onResponse(Response<Result<Series>> response) {
                calls.remove(call);
                callback.success(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                calls.remove(call);
                callback.error(t);
            }
        });
    }

    //</editor-fold>

    public void setTimeout(long timeoutInSeconds) {
        timeout = timeoutInSeconds;
        initHttpClient();
    }

    public void setLogLevel(HttpLoggingInterceptor.Level level) {
        logLevel = level;
        initLogging();
    }

    public void setCacheDirectory(File cacheDirectory) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        cache = new Cache(new File(cacheDirectory.getAbsolutePath() + "/" + "http"), cacheSize);
        initHttpClient();
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public void cancelAllRequests() {
        for (Call call : calls)
            call.cancel();
    }

    private String listToString(List<?> theList) {
        if (theList == null) return null;
        String s = "";
        for (Object o : theList) {
            if (o != null) {
                s += o.toString() + ",";
            }
        }
        return s;
    }

}
