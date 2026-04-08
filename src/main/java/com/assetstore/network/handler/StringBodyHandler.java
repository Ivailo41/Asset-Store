package com.assetstore.network.handler;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringBodyHandler implements BodyHandler<String> {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Override
    public void processChunk(ByteBuffer buffer) {

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        outStream.write(bytes, 0, bytes.length);
    }

    @Override
    public String getBody() {
        return outStream.toString(StandardCharsets.UTF_8);
    }
}
