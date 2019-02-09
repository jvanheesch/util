package com.github.jvanheesch.servlet;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;
import java.util.function.Consumer;

class OnCompleteAsyncListener implements AsyncListener {
    private final Consumer<AsyncEvent> onCompleteAction;

    private OnCompleteAsyncListener(Consumer<AsyncEvent> onCompleteAction) {
        this.onCompleteAction = onCompleteAction;
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        this.onCompleteAction.accept(event);
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        // do nothing
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        // do nothing
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        // do nothing
    }
}
