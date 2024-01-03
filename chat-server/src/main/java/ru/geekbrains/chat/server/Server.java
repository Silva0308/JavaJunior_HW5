package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT = 1425;
    private final ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void start() {
        System.out.print("Server started on ");
        try {
            System.out.printf("local ip=%s, port=%d\n",serverSocket.getInetAddress().getHostAddress(),serverSocket.getLocalPort());
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket);
                System.out.println("Client connected "+clientManager.getName()+" | "+clientManager.getSocketClient());
                Thread clientThread = new Thread(clientManager);
                clientThread.start();
            }
        } catch (IOException e) {

        }
    }



}