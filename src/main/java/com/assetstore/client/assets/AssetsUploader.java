package com.assetstore.client.assets;

import com.assetstore.network.SitpClient;
import com.assetstore.network.SitpRequest;
import com.assetstore.network.SitpResponse;
import com.assetstore.network.publisher.BodyPublisher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class AssetsUploader {

    private final SitpClient client;
    private final ExecutorService processExecutor;

    public AssetsUploader(SitpClient client, ExecutorService processExecutor) {
        this.client = client;
        this.processExecutor = processExecutor;
    }

    public CompletableFuture<String> uploadAsset(Path filePath) {
        try{
            SitpRequest request = new SitpRequest.Builder()
                    .url("localhost:8080/" + filePath.getFileName())
                    .header("Content-type", "file")
                    .POST(BodyPublisher.ofFile(filePath)).build();

            //simple response processing for now
            return client.sendAsync(request)
                    .thenApplyAsync(SitpResponse::message, processExecutor);
        }
        catch(IOException e){
            return CompletableFuture.failedFuture(e);
        }

    }
}
