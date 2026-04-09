package com.assetstore.network;

import com.assetstore.network.handler.BodyHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class SitpClient {

    private final ExecutorService executor;

    public SitpClient(ExecutorService executor) {
        this.executor = executor;
    }

    public<T> SitpResponse<T> send(SitpRequest request) {
        System.out.println("Sending from Thread: " + Thread.currentThread().getName());

        String requestHost = request.url().substring(0, request.url().indexOf('/'));
        String serverDomain = requestHost.substring(0, requestHost.indexOf(':'));
        String serverPort = requestHost.substring(requestHost.indexOf(':') + 1);
        String path = request.url().substring(request.url().indexOf('/') + 1);

        long contentLength = request.body().contentLength();
        request.headers().put("Content-Length", String.valueOf(contentLength));

        try(SocketChannel socketChannel = SocketChannel.open();
            BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, "UTF-8"));
            PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, "UTF-8"), true);
            )
        {
            socketChannel.connect(new InetSocketAddress(serverDomain, Integer.parseInt(serverPort)));

            StringBuilder response = new StringBuilder();
            response.append(request.method()).append(" ").append(request.url()).append("\r\n");

            for(Map.Entry<String, String> entry : request.headers().entrySet()) {
                response.append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue())
                        .append("\r\n");
            }
            response.append("\r\n");

            ByteBuffer headerBuffer = ByteBuffer.wrap(response.toString().getBytes(StandardCharsets.UTF_8));
            while (headerBuffer.hasRemaining()) {
                socketChannel.write(headerBuffer);
            }

            ByteBuffer bodyBuffer;
            while((bodyBuffer = request.body().nextChunk()) != null) {
                socketChannel.write(bodyBuffer);
            }

            //Wait for response
            //return SitpResponse
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Temporary null return until server respond logic is added
        return null;
    }

    public<T> CompletableFuture<SitpResponse<T>> sendAsync(SitpRequest request) {
        return CompletableFuture.supplyAsync(() -> send(request), executor);
    }
}
