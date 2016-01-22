package com.jayseeofficial.marvel.rest.interceptor;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jon on 20/01/16.
 */
public class AuthenticationInterceptor implements Interceptor {
    protected String privateKey;
    protected String publicKey;

    public AuthenticationInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String ts = ts();
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", hash(ts))
                .build();
        Request request = chain.request().newBuilder().url(url).build();
        Response response = chain.proceed(request);

        return response;
    }

    private String hash(String ts) {
        return new String(Hex.encodeHex(DigestUtils.md5(ts + privateKey + publicKey)));
    }

    private String ts() {
        return System.currentTimeMillis() + "";
    }
}
