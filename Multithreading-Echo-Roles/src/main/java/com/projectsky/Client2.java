package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter role:");

            String request;
            while ((request = stdin.readLine()) != null) {
                if (request.equals("end")){
                    System.out.println("Disconnecting from the server");
                    socket.close();
                    break;
                }
                out.println(request);

                String response = in.readLine();
                if (response == null){
                    break;
                }
                System.out.println(response);
            }
        }
    }
}
