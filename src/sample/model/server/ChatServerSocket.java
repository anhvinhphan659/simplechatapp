package sample.model.server;

import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerSocket extends Thread{
    private ServerSocket server;
    private Integer clientID;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private BufferedReader br;
    private BufferedWriter bw;
    private boolean isStopped=false;
    public ChatServerSocket(ServerSocket server,Socket socket,Integer clientID)
    {
        this.server=server;
        this.socket=socket;
        this.clientID=clientID;
        setUpIO();
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

                //write first data
                bw.write(String.valueOf(clientID));
                bw.newLine();
                bw.flush();

            } catch (IOException e) {
                System.out.println("Error while setting up IO socket");
                e.printStackTrace();
            }

        }
    }
    public void run()
    {
        do{
            try {
                String msg=br.readLine();
                System.out.println(msg);
                //handle data from here
                JSONObject jo=new JSONObject(msg);
                if(String.valueOf(jo.get("data")).equalsIgnoreCase("quit"))
                {
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error reading from socket "+String.valueOf(clientID));
                e.printStackTrace();
            }

        }while (true);
        closeIO();
    }
    public void closeIO()
    {


            try {
                if(br!=null) {
                    br.close();
                }
                if(bw!=null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String log="Client with ID=" +String.valueOf(clientID)+" has left!";
            System.out.println(log);
            ServerChat.updateLog(log);
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }
}
