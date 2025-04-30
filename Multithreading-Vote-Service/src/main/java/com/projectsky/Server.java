package com.projectsky;

import com.projectsky.model.Topic;
import com.projectsky.view.View;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static volatile boolean running = true;
    private static final ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<Socket> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            while (running) {
                try{
                    Socket socket = serverSocket.accept();
                    executorService.execute(() -> roleHandler(socket, serverSocket));
                } catch (IOException e) {
                    if (!running) {
                        System.out.println("Server closed by admin");
                    } else {
                        System.err.println("Server closed unexpectedly " + e.getMessage());
                    }
                    break;
                }
            }
            executorService.shutdownNow();
        }
    }

    private static void roleHandler(Socket socket, ServerSocket serverSocket) {
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            View view = new View(topics);
            view.setDataInputStream(in);
            view.setDataOutputStream(out);

            String role;
            while ((role = in.readUTF()) != null) {
                if (role.equals("admin") || role.equals("user")) {
                    break;
                }
                out.writeUTF("Invalid role");
            }

            if (role.equals("user")){
                clients.add(socket);
                userHandler(socket,in,out, view);
            } else {
                clients.add(socket);
                adminHandler(socket,serverSocket, in, out, view);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void adminHandler(Socket socket, ServerSocket serverSocket, DataInputStream in, DataOutputStream out, View view) {
        try{
            out.writeUTF("You are logged in as admin. Enter 'help' to see all available commands");
            System.out.println("Admin logged in");
            String request;
            while((request = in.readUTF()) != null){
                if(request.equals("exit")){ // exit
                    out.writeUTF("Shutting down the server...");
                    running = false;
                    socket.close();
                    serverSocket.close();
                    break;
                }
                if(request.trim().startsWith("echo")){ // echo
                    view.printEcho(request);
                    continue;
                }
                if(request.equals("time")){ // time
                    view.printTime();
                    continue;
                }
                if(request.startsWith("view -t=") && !request.contains("-v")){ // view -t=<topic>
                    view.printVotesByTopic(request);
                    continue;
                }
                if(request.contains("view") && request.contains("-t") && request.contains("-v")){ // view -t=<topic> -v=<vote>
                    List<String> params = view.getParams(request, "-t", "-v");
                    view.printInfoByTopicAndVote(params.get(0), params.get(1));
                    continue;
                }
                if(request.startsWith("vote") && request.contains("-t") && request.contains("-v")){ // vote -t=<topic> -v=<vote>
                    view.doVote(request);
                    continue;
                }
                if(request.startsWith("create topic -n=")){ // create topic -n=<name>
                    view.doCreateTopic(request);
                    continue;
                }
                if(request.startsWith("create vote -t=")){ // create vote -t=<topic>
                    view.doCreateVote(request);
                    continue;
                }
                if(request.equals("clients")){ // clients
                    view.printActiveUsers(clients);
                    continue;
                }
                if(request.equals("help")){ // help
                    view.printHelpMenuForAdmin();
                } else{
                    out.writeUTF("Invalid command");
                }
            }
            System.out.println("Admin disconnected");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void userHandler(Socket socket, DataInputStream in, DataOutputStream out, View view) {
        try{
            out.writeUTF("You are logged in as user. Enter 'help' to see all available commands.");
            System.out.println("User logged in");
            String request;
            while((request = in.readUTF()) != null){
                if(request.equals("exit")){ // exit
                    out.writeUTF("Permission denied");
                    continue;
                }
                if(request.trim().startsWith("echo")){ //echo
                    view.printEcho(request);
                    continue;
                }
                if(request.equals("time")){ // time
                    view.printTime();
                    continue;
                }
                if(request.startsWith("view -t=")){ // view -t=<topic>
                    view.printVotesByTopic(request);
                    continue;
                }
                if(request.startsWith("view") && request.contains("-t") && request.contains("-v")){ // view -t=<topic> -v=<vote>
                    List<String> params = view.getParams(request, "-t", "-v");
                    view.printInfoByTopicAndVote(params.get(0), params.get(1));
                    continue;
                }
                if(request.startsWith("vote") && request.contains("-t") && request.contains("-v")){ // vote -t=<topic> -v=<vote>
                    view.doVote(request);
                    continue;
                }
                if(request.startsWith("create")){ // create
                    out.writeUTF("Permission denied");
                    continue;
                }
                if(request.equals("clients")){ // clients
                    out.writeUTF("Permission denied");
                    continue;
                }
                if(request.equals("help")){ // help
                    view.printHelpMenuForUser();
                } else {
                    out.writeUTF("Invalid command");
                }
            }
            System.out.println("User disconnected");
        } catch (Exception e) {
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
