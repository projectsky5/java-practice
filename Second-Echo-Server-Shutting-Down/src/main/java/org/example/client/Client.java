package org.example.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String request = in.readLine();
            while (!request.equalsIgnoreCase("exit")) {
                out.println(request);
                String response = bufferedReader.readLine();
                System.out.println(response);
                request = in.readLine();
            }
        }
    }
}