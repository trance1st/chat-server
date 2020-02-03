package ro.posa;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientThread extends Thread {

    Socket socketClient;
    Socket socketDest;
    HashMap<String, Socket> allClients;

    @Override
    public void run() {
        try {
            InputStream inputStreamClient = socketClient.getInputStream();
            OutputStream outputStreamClient = socketClient.getOutputStream();
            BufferedWriter writerClient = new BufferedWriter(new OutputStreamWriter(outputStreamClient));
            BufferedReader readerClient = new BufferedReader(new InputStreamReader(inputStreamClient));

            writerClient.write("Introdu un nume" +"\n");
            writerClient.flush();
            String clientName = readerClient.readLine();
            allClients.put(clientName, socketClient);
            String allClientsContent = "";
            for(String c: allClients.keySet()) {
                allClientsContent = allClientsContent + c +",";
            }
            writerClient.write(allClientsContent  + "\n");
            writerClient.flush();
            String dest = readerClient.readLine();
            socketDest = allClients.get(dest);

            InputStream inputStreamDest = socketDest.getInputStream();
            OutputStream outputStreamDest = socketDest.getOutputStream();
            BufferedWriter writerDest = new BufferedWriter(new OutputStreamWriter(outputStreamDest));
            BufferedReader readerDest = new BufferedReader(new InputStreamReader(inputStreamDest));

            while (true) {
                writerDest.write(readerClient.readLine() + "\n");
                writerDest.flush();
            }
        } catch (Exception e) {

        }
    }
}
