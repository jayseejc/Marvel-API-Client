package com.jayseeofficial.marvel.rest;

import com.jayseeofficial.marvel.rest.model.Result;

/**
 * Created by jon on 20/01/16.
 */
public interface Callback<T> {
    void success(Result<T> result);

    void error(Throwable throwable);
}
