package org.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234);
             Socket socket = serverSocket.accept();
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream())){
            String request = inputStream.readUTF();
            System.out.println(request);
            while(!"stop".equals(request)){
                System.out.println("server socket received: " + request);
                outputStream.writeUTF(request + " [echo]");
                System.out.println("server socket delivered");
                request = inputStream.readUTF();
            }
        }
    }
}
