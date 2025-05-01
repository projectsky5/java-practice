package com.projectsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendMessage() {
        byte[] buffer = new byte[1024];
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            DatagramSocket socket = new DatagramSocket();
            while ((message = in.readLine()) != null) {
                if (message.equals("end")){
                    break;
                }
                byte[] data = message.getBytes();
                InetAddress address = InetAddress.getByName(host);
                DatagramPacket packOut = new DatagramPacket(data, data.length, address, port);
                socket.send(packOut);

                DatagramPacket packIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(packIn);
                String messageIn = new String(packIn.getData(), 0, packIn.getLength());
                System.out.println(messageIn);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



}
