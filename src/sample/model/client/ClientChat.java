package sample.model.client;

import java.io.IOException;
import java.net.Socket;

public class ClientChat
{

    public static void initializeClient(String address,int port)
    {
        try {
            Socket socket=new Socket(address,port);
            ClientSocket clientSocket=new ClientSocket(socket);
            clientSocket.start();
        } catch (IOException e) {
            System.out.println("Error while initializing socket!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeClient("localhost",8000);
    }
}
