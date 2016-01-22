package com.jayseeofficial.marvel.rest.test;

import com.google.common.util.concurrent.SettableFuture;
import com.jayseeofficial.marvel.rest.Callback;
import com.jayseeofficial.marvel.rest.RestClient;
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
        String publicKey = "YOUR PUBLIC KEY HERE";
        String privateKey = "YOUR PRIVATE KEY HERE";

        assertThat(publicKey).isNotEmpty();
        assertThat(publicKey).isNotEqualTo("YOUR PUBLIC KEY HERE");
        assertThat(privateKey).isNotEmpty();
        assertThat(privateKey).isNotEqualTo("YOUR PRIVATE KEY HERE");

        restClient = new RestClient(publicKey, privateKey);
    }

    protected class TestCallback implements Callback {

        private SettableFuture future;

        public TestCallback(SettableFuture future) {
            this.future = future;
        }

        @Override
        public void success(Result result) {
            future.set(result);
        }

        @Override
        public void error(Throwable throwable) {
            future.setException(throwable);
        }
    }
}
