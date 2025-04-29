package com.projectsky7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            System.out.println("Server Started");

            boolean running = true;
            while (running) {
                try(Socket socket = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)){
                    System.out.println("Client connected");

                    String request = bufferedReader.readLine();
                    while (request != null && !request.equalsIgnoreCase("end")) {
                        if(request.equals("exit")){
                            System.out.println("Shutting down server");
                            serverSocket.close();
                            System.exit(0);
                        }
                        printWriter.println("Message received: " + request);
                        request = bufferedReader.readLine();

                    }
                }
                System.out.println("Client disconnected");
            }
        }
    }
}
