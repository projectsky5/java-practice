package com.projectsky.client;

import java.io.*;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please enter you role: ");
            String request;
            while ((request = stdin.readLine()) != null) {
                if (request.equals("end")) {
                    break;
                }
                out.writeUTF(request);
                String response;
                if((response = in.readUTF()) == null){
                    break;
                }
                System.out.println(response);

            }
        }
    }
}
