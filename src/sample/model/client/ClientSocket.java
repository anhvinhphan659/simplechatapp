package sample.model.client;

import org.json.JSONObject;
import sample.model.util.MessageJSON;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket extends Thread{
    private Integer clientID;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private BufferedReader br;
    private BufferedWriter bw;
    public ClientSocket(Socket socket)
    {
        this.socket=socket;
        setUpIO();
        Thread writeThread=new Thread(){
            @Override
            public void run() {

                Scanner sc=new Scanner(System.in);
                while (true)
                {
                    String line=sc.nextLine();
                    try {

                        JSONObject msgJSON= MessageJSON.createMessageJSON(clientID.toString(),"","text",line);
                        bw.write(msgJSON.toString());
                        bw.newLine();
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        writeThread.start();
    }

    private void setUpIO()
    {
        if(socket!=null)
        {
            try {
                is=socket.getInputStream();
                os=socket.getOutputStream();
                br=new BufferedReader(new InputStreamReader(is));
                bw=new BufferedWriter(new OutputStreamWriter(os));

                //read first data
                String line=br.readLine();
                clientID=Integer.parseInt(line);
                System.out.println(line);
            } catch (IOException e) {
                System.out.println("Error while setting up IO socket");
                e.printStackTrace();
            }

        }
    }
    @Override
    public void run() {
       while (true)
       {
           try {
               String line=br.readLine();
               System.out.println(line);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }
}
