package com.jayseeofficial.marvel.rest.interceptor;

import com.google.gson.Gson;
import com.jayseeofficial.marvel.rest.exception.TooManyRequestsException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jon on 20/01/16.
 */
public class AuthenticationInterceptor implements Interceptor {
    private static final String TIMESTAMP_FILE = "timestamps.json";
    protected String privateKey;
    protected String publicKey;
    protected File cacheDir;
    protected ConcurrentHashMap<String, String> timeStamps;

    public AuthenticationInterceptor(String publicKey, String privateKey, File cacheDir) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.cacheDir = cacheDir;

        if (cacheDir != null) {
            cacheDir.mkdirs();
            File tsFile = new File(cacheDir.getAbsolutePath() + "/" + TIMESTAMP_FILE);
            if (tsFile.exists()) {
                try {
                    timeStamps = new Gson().fromJson(new FileReader(tsFile), ConcurrentHashMap.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                timeStamps = new ConcurrentHashMap<String, String>();
                saveTimestamps();
            }
        } else
            timeStamps = new ConcurrentHashMap<String, String>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String ts = ts(chain.request().url().toString());
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", hash(ts))
                .build();
        Request request = chain.request().newBuilder().url(url).build();
        Response response = chain.proceed(request);

        if (response.code() == 429) throw new TooManyRequestsException(response.message());

        return response;
    }

    private String hash(String ts) {
        return new String(Hex.encodeHex(DigestUtils.md5(ts + privateKey + publicKey)));
    }

    private String ts(String path) {
        String ts;
        if (cacheDir == null)
            ts = System.currentTimeMillis() + "";
        else {
            if (timeStamps.containsKey(path))
                ts = timeStamps.get(path);
            else {
                ts = System.currentTimeMillis() + "";
                timeStamps.put(path, ts);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        saveTimestamps();
                    }
                }).start();
            }
        }
        return ts;
    }

    private synchronized void saveTimestamps() {
        String json = new Gson().toJson(timeStamps);
        FileWriter writer = null;
        try {
            if (!cacheDir.exists()) cacheDir.mkdirs();
            File file = new File(cacheDir.getAbsolutePath() + "/" + TIMESTAMP_FILE);
            if (!file.exists()) file.createNewFile();
            writer = new FileWriter(file, false);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException e) {
                // Well.... We tried.
                e.printStackTrace();
            }
        }
    }
}
