package com.jayseeofficial.marvel.rest;

import com.jayseeofficial.marvel.rest.model.Result;

/**
 * Created by jon on 29/01/17.
 */

public interface SuccessCallback<T> {
    void call(Result<T> result);
}
