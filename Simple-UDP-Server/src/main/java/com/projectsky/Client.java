package com.projectsky;

import java.io.IOException;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    private final String host;
    private final int port;

    public Client(String address, int port) {
        this.host = address;
        this.port = port;
    }

    /*
    1. Создается host для хранения адреса сервера к которому будем подключаться
    2. Создается port, который является портом сервера
    3. Метод sendMessage(String message):
        1. Создается byte[] data который считывает размер message для будущей его отправки
        2. Получается InetAddress, что является обязательный для работы с UDP
        3. Создается пакет с контейнером data, размером с размер data, адресом и портом (чтобы понимать куда именно его отправить)
        4. Создается сокет который необходим для отправки пакета
        5. Отправляется пакет с помощью сокета
        6. Сокет закрывается, т.к данные отправляется фиксированно одной отправкой, после которой сокет надо закрыть, чтобы потом открыть его снова
    4. Создается объект Client с указанными адресом и портом сервера
    5. Инициализируется сообщение которое будем передавать
    6. Создается таймер для тестирования работы
    7. Запускается отправка сообщения путем вызова sendMessage у клиента каждую секунду

    Порядок выполнения sendMessage внутри таймера:
    1. byte[] data = Hello World!.getBytes(); - Определили размер строки Hello World! == 12
    2. Нашли адрес с помощью хоста - localhost (немного не понимаю что за InetAddress, но сам поищу в интернете, сейчас объяснять не надо)
    3. Создали пакет, с хранилищем = 12 байтам, размер пакета будет 12 байт, будет хранить в себе адрес клиента, порт клиента
    4. Создали просто сокет
    5. Отправили пакет
    6. Закрыли сокет чтобы потом начать это все сначала

    Вопросы которые возникли:
    1. Если мы создаем host и port у клиента, и передаем их в пакете, значит сервер по пакету знает адрес и порт клиента, но как тогда клиент узнает адрес сервера по которому надо отправить данные?
    2. Зачем закрывать сокет после отправки данных
     */

    private void tradeMessages(String message) {
        try{
            byte[] data = message.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packOut = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packOut);

            byte[] buffer = new byte[1024];
            DatagramPacket packIn = new DatagramPacket(buffer, buffer.length);
            socket.receive(packIn);
            System.out.println(new String(packIn.getData(), 0, packIn.getLength()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        String message = "Hello World!";

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                client.tradeMessages(message);
            }
        }, 1000, 1000);
    }
}
