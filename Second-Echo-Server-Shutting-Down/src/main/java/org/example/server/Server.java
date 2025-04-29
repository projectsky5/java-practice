package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started");

            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                running = handleClient(socket);

                if(!running){
                    System.out.println("Client disconnected");
                    serverSocket.close();
                    System.exit(0);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean handleClient(Socket socket) {
        boolean flag = true;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {
            String request;
            while ((request = bufferedReader.readLine()) != null) {
                if (request.equals("exit")) {
                    flag = false;
                    break;
                }
                printWriter.println(request + " [echo]");
            }
        } catch (IOException e){
            System.out.println("Ошибка ввода");
        }
        return flag;
    }
}