package com.jayseeofficial.marvel.rest.test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.TimeoutException;

/**
 * Created by jon on 29/01/17.
 */

public class RetryTestRule implements TestRule {
    int retryCount;
    int failureCount = 0;

    public RetryTestRule(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override public void evaluate() throws Throwable {
                Throwable caughtException = null;
                for (int i = 0; i < retryCount; i++) {
                    try {
                        base.evaluate();
                        System.out.println(description.getTestClass().getCanonicalName() +
                                           "." +
                                           description.getMethodName() +
                                           ": Success after " + failureCount + " failures!");
                        return;
                    } catch (TimeoutException t) {
                        caughtException = t;
                        System.err.println("Timeout on " +
                                           description.getDisplayName() +
                                           " " + ++failureCount);
                    }
                }
                System.err.println(description.getDisplayName() +
                                   ": giving up after " +
                                   failureCount +
                                   " failures");
                throw caughtException;
            }
        };
    }
}
