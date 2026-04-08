package com.assetstore.network.publisher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileBodyPublisher implements BodyPublisher {

    static final int BUFFER_SIZE = 8192;

    private final FileChannel channel;
    private final long size;
    private long position = 0;

    public FileBodyPublisher(Path path) throws IOException {
        this.channel = FileChannel.open(path, StandardOpenOption.READ);
        this.size = channel.size();
    }

    @Override
    public ByteBuffer nextChunk() throws IOException {
        if(position >= size) {
            return null;
        }

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int read = channel.read(buffer, position);
        position += read;

        buffer.flip();
        return buffer;
    }

    @Override
    public long contentLength() {
        return size;
    }
}
