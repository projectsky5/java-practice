package com.projectsky;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(9999);
            byte[] buffer = new byte[1024];

            while(true){
                DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(packetIn);
                InetAddress address = packetIn.getAddress();
                int port = packetIn.getPort();
                String messageIn = new String(packetIn.getData(), 0, packetIn.getLength(), StandardCharsets.UTF_8);

                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String messageOut = "ACK: " + messageIn + String.format(" [time: %s]", currentTime);
                DatagramPacket packetOut = new DatagramPacket(messageOut.getBytes(StandardCharsets.UTF_8), messageOut.length(), address, port);
                socket.send(packetOut);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
