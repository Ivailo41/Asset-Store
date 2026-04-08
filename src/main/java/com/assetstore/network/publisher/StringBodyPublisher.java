package com.assetstore.network.publisher;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringBodyPublisher implements BodyPublisher {

    private final ByteBuffer buffer;

    public StringBodyPublisher(String string) {

        if(string == null) {
            this.buffer = ByteBuffer.allocate(0);
        }
        else {
            this.buffer = ByteBuffer.wrap(string.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public ByteBuffer nextChunk() {
        if (!buffer.hasRemaining()) {
            return null;
        }

        return buffer;
    }

    @Override
    public long contentLength() {
        return buffer.remaining();
    }
}
