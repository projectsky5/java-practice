package com.projectsky;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws SocketException {
        try{
            DatagramSocket socket = new DatagramSocket(9999);
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(packetIn);

                InetAddress address = packetIn.getAddress();
                int port = packetIn.getPort();

                String messageInput = new String(packetIn.getData(), 0, packetIn.getLength());

                String messOut = "ACK: " + messageInput;

                DatagramPacket packetOut = new DatagramPacket(messOut.getBytes(), messOut.length(), address, port);
                socket.send(packetOut);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

/* 1. Создается DatagramSocket на порту 9999 который "Слушает" запросы к нему и готов их принять
   2. Создается буффер на 1024, который служит контейнером для принимаемых данных (в данный момент на текущей строке просто инициализирован, ничего не делает)
   3. Вечный цикл для постоянной работы
   4. Создается пакет, который служит контейнером для принимаемого запроса, параметры пакета:
        1. буффер, который будет служить как раз тем самым контейнером (можно было сделать и new Byte[1024])
        2. размер принимаемых данных из запроса (например если размер буффера будет 10 байт, а в строке 12 символов, то лишнее обрежется)
   5. сокет принимает данный пакет (пакет служит лишь в роли объекта напоминающего dto)
   6. Выводится на экран содержимое полученного пакета путем вызова getData у пакета
 */