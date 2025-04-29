package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static volatile boolean running = true;
    private static final CopyOnWriteArrayList<Socket> clients = new CopyOnWriteArrayList<>();
    private static final String PERMISSION_DENIED = "Permission denied";

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);

            while (running) {
                try{
                    Socket socket = serverSocket.accept();
                    executor.execute(() -> roleHandler(socket, serverSocket));
                } catch (IOException e){
                    if (!running) {
                        System.out.println("Server stopped by admin");
                    } else {
                        System.out.println("Server stopped unexpectedly: " + e.getMessage());
                    }
                    break;
                }
            }
            executor.shutdownNow();
        }
    }

    private static void roleHandler(Socket socket, ServerSocket serverSocket){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String role;
            while ((role = in.readLine()) != null) {
                if (role.equals("admin") || role.equals("user")) {
                    break;
                }
                out.println("Invalid command, select a role:");
            }

            if(role.equals("admin")){
                adminHandler(socket, serverSocket, in, out);
            } else {
                clients.add(socket);
                userHandler(socket, in, out);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void userHandler(Socket socket, BufferedReader in, PrintWriter out) {
        try {
            System.out.println("User connected");
            out.println("You entered as user");
            String request;

            while((request = in.readLine()) != null) {
                if(request.equals("exit")){
                    out.println(PERMISSION_DENIED);
                    continue;
                }
                if(request.equals("clients")){
                    out.println(PERMISSION_DENIED);
                    continue;
                }
                if(request.equals("time")){
                    out.println("Current time is " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    continue;
                }
                if(request.trim().startsWith("echo")){
                    String response = request.substring("echo".length()).isEmpty() ? " Entered blank message" : request.substring("echo".length());
                    out.println("Echo:" + response);
                } else {
                    out.println("Invalid command");
                }
            }
            if(!clients.isEmpty()){
                clients.remove(socket);
            }
            System.out.println("User disconnected");
        } catch (Exception e){
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void adminHandler(Socket socket, ServerSocket serverSocket, BufferedReader in, PrintWriter out) {
        try {
            System.out.println("Admin connected");
            out.println("You are entered as admin");
            String request;
            while ((request = in.readLine()) != null) {
                if(request.equals("exit")){
                    running = false;
                    socket.close();
                    serverSocket.close();
                    break;
                }
                if(request.equals("clients")){
                    out.println("Number of active users: " + clients.size());
                    continue;
                }
                if(request.equals("time")){
                    out.println("Current time is " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    continue;
                } if(request.trim().startsWith("echo")){
                    String response = request.substring("echo".length()).isEmpty() ? " Entered blank message" : request.substring("echo".length());
                    out.println("Echo:" + response);
                } else {
                    out.println("Invalid command");
                }
            }
            if(!clients.isEmpty()){
                clients.remove(socket);
            }
            System.out.println("Admin disconnected");
        } catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
