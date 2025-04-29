package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static volatile boolean running = true;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);

            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.execute(() -> {
                        roleHandler(socket, serverSocket);
                    });
                } catch (IOException e) {
                    if(!running) {
                        System.out.println("Server stopped by admin");
                    } else {
                        System.out.println("Server stopped unexpectedly " + e.getMessage());
                    }
                    break;
                }
            }
            executor.shutdownNow();

        }
    }

    private static void roleHandler(Socket socket, ServerSocket serverSocket) {

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String roleCommand;

            while ((roleCommand = in.readLine()) != null) {
                if (roleCommand.equals("user") || roleCommand.equals("admin")) {
                    break;
                }
                out.println("Invalid command: " + roleCommand);
            }
            if (roleCommand.equals("user")) {
                System.out.println("User connected");
                out.println("You are logged in as user");
                userHandler(socket, in,out);
            } else {
                System.out.println("Admin connected");
                out.println("You are logged in as admin");
                adminHandler(socket, serverSocket, in,out);
            }
        } catch (IOException e) {
            System.out.println("Error handling connection: " + e.getMessage());
        }
    }


    private static void userHandler(Socket socket, BufferedReader in, PrintWriter out) {
        try{
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("exit")) {
                    out.println("Permission denied");
                }
                out.println("Echo: " + request);
            }
            System.out.println("User disconnected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void adminHandler(Socket socket, ServerSocket serverSocket, BufferedReader in, PrintWriter out){
        try{
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("exit")) {
                    out.println("Shutting down the server...");
                    running = false;
                    socket.close();
                    serverSocket.close();
                }
                out.println("Echo: " + request);
            }
            System.out.println("Admin disconnected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
