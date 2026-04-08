package com.assetstore.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import java.net.http.HttpClient;

public class Client {
    private static final int SERVER_PORT = 8080;

    static void main() {

        try (SocketChannel socketChannel = SocketChannel.open();
             BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, "UTF-8"));
             PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, "UTF-8"), true);
             Scanner scanner = new Scanner(System.in);
             FileChannel fileChannel = new FileInputStream("src/main/java/com/assetstore/assets.txt").getChannel()) {

            socketChannel.connect(new InetSocketAddress("localhost", SERVER_PORT));

            System.out.println("Connected to the server.");

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine(); // read a line from the console

                if ("quit".equals(message)) {
                    break;
                }

                System.out.println("Sending message <" + message + "> to the server...");


                fileChannel.transferTo(0,fileChannel.size(), socketChannel);

                //writer.println(message);

                String reply = reader.readLine(); // read the response from the server
                System.out.println("The server replied <" + reply + ">");
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File Not found", e);
        }
        catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }
}
