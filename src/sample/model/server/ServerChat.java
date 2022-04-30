package sample.model.server;

import sample.controller.server.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerChat
{
    public static int PORT=8000;
    private static final int MAX_CLIENT=20;
    private static HashMap<Integer,ChatServerSocket> client_server_List;
    private static int CURRENT=0;

    private static boolean STATE=true;
    private static ServerSocket server;
    private static ServerController serverController;
    public static void initializeServer(ServerController controller, int port)
    {

        try {
            serverController =controller;
            PORT=port;
            server = new ServerSocket(PORT);
            client_server_List=new HashMap<>();
            do {
                System.out.println("Waiting for client");

                if(client_server_List.size()<MAX_CLIENT) {
                    Socket connect = server.accept();
                    ChatServerSocket chatServerSocket=new ChatServerSocket(server,connect, CURRENT);
                    CURRENT++;
                    //start thread when socket is established
                    chatServerSocket.start();
                    String log="Client with ID= "+String.valueOf(chatServerSocket.getClientID())+" is accepted";
                    System.out.println(log);
                    serverController.updateLog(log);
                }

            }while (STATE);

            System.out.println("Server closes");


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void closeServer()
    {
        if(server!=null)
        {
            try {

                Socket socket=new Socket("localhost",PORT);
                server.close();
                STATE=false;
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    public static void updateLog(String log)
    {
        serverController.updateLog(log);
    }

//    public static void main(String[] args) {
//        initializeServer(DEFAULT_PORT);
//    }
}
