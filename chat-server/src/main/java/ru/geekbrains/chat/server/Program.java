package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.UnknownHostException;

public class Program {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}


