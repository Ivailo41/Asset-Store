package com.assetstore.network.publisher;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBodyPublisherTest {

    private static String handlePublisher(BodyPublisher bodyPublisher) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ByteBuffer chunk;

        while ((chunk = bodyPublisher.nextChunk()) != null) {
            byte[] bytes = new byte[chunk.remaining()];
            chunk.get(bytes);
            out.write(bytes, 0, bytes.length);
        }

        return out.toString(StandardCharsets.UTF_8);
    }

    @Test
    void testCreationWithSimpleString() throws IOException {

        BodyPublisher publisher = BodyPublisher.ofString("Hello World");

        assertEquals("Hello World", handlePublisher(publisher));
    }

    @Test
    void testCreationWithEmptyString() throws IOException {
        BodyPublisher publisher = BodyPublisher.ofString("");

        assertEquals("", handlePublisher(publisher));
    }

    @Test
    void testCreationWithNullString() throws IOException {
        BodyPublisher publisher = BodyPublisher.ofString(null);
        assertEquals("", handlePublisher(publisher));
    }
}
