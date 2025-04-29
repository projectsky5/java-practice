package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client3 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            String request;
            while ((request = stdin.readLine()) != null && !request.equals("end")) {
                out.println(request);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
