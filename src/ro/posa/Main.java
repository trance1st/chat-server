package ro.posa;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        HashMap<String, Socket> allClients = new HashMap<>();
        while (true) {
            Socket socket = serverSocket.accept();
            ClientThread clientThread = new ClientThread();
            clientThread.socketClient = socket;
            clientThread.allClients = allClients;
            clientThread.start();
        }
    }
}
