package com.assetstore.client;

import com.assetstore.client.assets.AssetsUploader;
import com.assetstore.client.command.Command;
import com.assetstore.client.command.CommandRegistry;
import com.assetstore.client.command.InMemoryCommandRegistry;
import com.assetstore.network.SitpClient;
import com.assetstore.network.SitpRequest;
import com.assetstore.network.publisher.BodyPublisher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    static void main() {

        //CommandRegistry commandRegistry = new InMemoryCommandRegistry();

        try(ExecutorService ioExecutor = Executors.newFixedThreadPool(2);
            ExecutorService processExecutor = Executors.newCachedThreadPool();
            Scanner scanner = new Scanner(System.in)) {

            SitpClient client = new SitpClient(ioExecutor);
            AssetsUploader assetsUploader = new AssetsUploader(client, processExecutor);

            //Will throw  null pointer exception because the returned response is null temporary
            assetsUploader.uploadAsset(Path.of("Input.txt"))
                    .thenAcceptAsync(System.out::println, processExecutor)
                    .join();

            /*while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine(); // read a line from the console

                if ("quit".equals(message)) {
                    break;
                }
            }*/
        }

    }
}
