package com.projectsky7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws IOException{
        try (Socket socket = new Socket("localhost", 7777);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            String request = stdin.readLine(); //end
            while(request != null) { //true
                switch (request) {
                    case "end" -> {
                        System.out.println("Disconnected from the server");
                        socket.close();
                        System.exit(0);
                    }
                    case "exit" -> {
                        System.out.println("Shutting down application");
                        out.println("exit");
                        socket.close();
                        System.exit(0);
                    }
                    default -> {
                        out.println(request);
                        System.out.println(in.readLine());
                        request = stdin.readLine();
                    }
                }
            }
        }
    }
}
