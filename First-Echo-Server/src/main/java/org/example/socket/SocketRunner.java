package org.example.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1234);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                dataOutputStream.writeUTF(scanner.nextLine());
                System.out.println(dataInputStream.readUTF());
            }
        }

    }
}
