package com.jayseeofficial.marvel.rest.interceptor;

import com.jayseeofficial.marvel.rest.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jon on 20/01/16.
 */
public class UserAgentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(
                chain.request().newBuilder()
                     .header("User-Agent", Constants.USER_AGENT)
                     .build()
        );
    }
}
