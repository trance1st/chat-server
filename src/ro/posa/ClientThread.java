package ro.posa;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String command = reader.readLine();
            command = command.replace("cat face ", "");
            String[] nrs = command.split("plus");
            writer.write("Rezultatul este " +
                    (Integer.parseInt(nrs[0].trim()) + Integer.parseInt(nrs[1].trim())) +"\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {

        }
    }
}
