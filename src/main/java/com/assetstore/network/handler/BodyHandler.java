package com.assetstore.network.handler;

import java.nio.ByteBuffer;

public interface BodyHandler<T> {

    static BodyHandler<String> ofString() {
        return new StringBodyHandler();
    }

    void processChunk(ByteBuffer buffer);

    T getBody();
}
