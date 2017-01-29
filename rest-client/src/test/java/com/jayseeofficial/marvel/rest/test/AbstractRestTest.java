package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.Constants;
import com.jayseeofficial.marvel.rest.FailureCallback;
import com.jayseeofficial.marvel.rest.RestClient;
import com.jayseeofficial.marvel.rest.SuccessCallback;
import com.jayseeofficial.marvel.rest.model.Result;

import org.junit.Before;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by jon on 20/01/16.
 */

public class AbstractRestTest {

    protected RestClient restClient;

    protected final int TIMEOUT = 90;

    @Before
    public void before() {
        String publicKey = Constants.PUBLIC_KEY;
        String privateKey = Constants.PRIVATE_KEY;

        assertThat(publicKey).isNotEmpty();
        assertThat(publicKey).isNotEqualTo("YOUR PUBLIC KEY HERE");
        assertThat(privateKey).isNotEmpty();
        assertThat(privateKey).isNotEqualTo("YOUR PRIVATE KEY HERE");

        restClient = new RestClient(publicKey, privateKey);
    }

    protected class TestSuccessCallback implements SuccessCallback {
        private SettableFuture future;

        public TestSuccessCallback(SettableFuture future) {
            this.future = future;
        }

        public void call(Result result) {
            future.set(result);
        }
    }

    protected class TestFailureCallback implements FailureCallback {
        private SettableFuture future;

        public TestFailureCallback(SettableFuture future) {
            this.future = future;
        }

        @Override
        public void call(Throwable throwable) {
            future.setException(throwable);
        }
    }
}
