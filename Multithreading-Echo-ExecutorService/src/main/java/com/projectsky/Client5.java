package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client5 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {

            String request = stdin.readLine();
            while (request != null && !request.equals("end")) {
                if (request.equals("exit")){
                    out.println("exit");
                    socket.close();
                    break;
                }
                out.println(request);
                System.out.println(in.readLine());
                request = stdin.readLine();
            }
        }
    }
}