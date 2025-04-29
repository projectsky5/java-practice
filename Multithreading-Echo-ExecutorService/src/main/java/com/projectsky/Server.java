package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static volatile boolean running = true;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             ExecutorService executor = Executors.newFixedThreadPool(5)) {

            while (running) {
                try{
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected");

                    executor.execute(() -> handleClient(socket, serverSocket));
                } catch (IOException e) {
                    if (!running) {
                        System.out.println("Server closed normally");
                    } else {
                        System.out.println("Server closed unexpectedly: " + e.getMessage());
                    }
                    break;
                }
            }
            executor.shutdown();
        } catch (SocketException e){
            System.out.println("Server stopped by reason: " + e.getMessage());
        }

    }

    private static void handleClient(Socket socket, ServerSocket serverSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("exit")) {
                    running = false;
                    serverSocket.close();
                }
                out.println("Echo: " + request);
            }
            System.out.println("Client disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}