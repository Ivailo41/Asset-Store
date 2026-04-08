package com.assetstore.network.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileBodyHandler implements BodyHandler<Path> {

    private final FileChannel fileChannel;
    private final Path path;

    public FileBodyHandler(Path path) throws IOException {
        this.fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        this.path = path;
    }

    @Override
    public void processChunk(ByteBuffer buffer) {
        try {
            while (buffer.hasRemaining()) {
                fileChannel.write(buffer);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path getBody() {
        return path;
    }

    public void close() throws IOException {
        fileChannel.close();
    }
}
