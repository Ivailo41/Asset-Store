package com.assetstore.network.publisher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public interface BodyPublisher {

    static BodyPublisher ofString(String string) {
        return new StringBodyPublisher(string);
    }

    static BodyPublisher ofFile(Path path) throws IOException {
        return new FileBodyPublisher(path);
    }

    ByteBuffer nextChunk() throws IOException;

    long contentLength();
}
