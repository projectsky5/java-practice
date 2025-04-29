package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Client connected");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String request = in.readLine();
            while (request != null) {
                out.println("Echo: " + request);
                request = in.readLine();
            }
            System.out.println("Client disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
