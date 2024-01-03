package ru.geekbrains.chat.client;

import ru.geekbrains.chat.client.exept.AppExeption;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Program {
    private static String serverIp = "127.0.0.1";
    private static int port = 1425;

    public static void main(String[] args) {
        System.out.println("Client start...");
        try {
            //добавляем имя из аргументов запуска
            String name;
            if (args.length == 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите свое имя:");
                name = scanner.nextLine();
            } else {
                name = args[0];
            }
            Client client = new Client(serverIp, port, name);
            Socket socket = client.connect();
            if (socket == null) {
                throw new AppExeption("Нет соединения с сервером");
            }
            InetAddress inetAddress = socket.getInetAddress();
            String remoteIP = inetAddress.getHostAddress();
            System.out.println("Сервер: " + remoteIP+":" + socket.getLocalPort());
            System.out.println("Ваш логин @"+name);
            System.out.println("--------------------------------------");
            client.listenForMessage();
            client.sendMessage();

        } catch (AppExeption e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Ручное прерывание");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
            e.printStackTrace();
        }

    }
}