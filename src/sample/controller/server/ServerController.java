package sample.controller.server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.server.ServerChat;

import java.lang.ref.PhantomReference;

public class ServerController {
    private static Thread mainThread=null;
    private static ServerRun serverRun=null;
    private static int PORT=8000;
    @FXML
    private TextField ipTF;
    @FXML
    private TextField portTF;
    @FXML
    private Button startBtn;
    @FXML
    private TextArea logTA;
    @FXML
    private ListView clientListLV;
    public void initialize() {
        initUI();
    }

    private void initUI()
    {
        //handle ip of computer
        ipTF.setText("localhost");
        portTF.setText("8000");
        logTA.setEditable(false);
    }

    @FXML
    public void startServer()
    {
        PORT=Integer.parseInt(portTF.getText());


        if(mainThread!=null&&startBtn.getText().equalsIgnoreCase("STOP"))
        {
            if(mainThread.isAlive()) {
                System.out.println("Close serversocket");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        startBtn.setText("START");
                        ipTF.setEditable(true);
                        portTF.setEditable(true);
                    }
                });
                serverRun.closeServer();
//                System.exit(0);
            }
            return;
        }
        serverRun=new ServerRun();
        mainThread=new Thread(serverRun);
        mainThread.start();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logTA.setText("");
                logTA.appendText("Server started at address \""+ipTF.getText()+"\" with port "+String.valueOf(PORT)+"\n");

                startBtn.setText("STOP");
                ipTF.setEditable(false);
                portTF.setEditable(false);
            }
        });


    }

    public void updateLog(String log)
    {
        logTA.appendText(log+"\n");
    }

    class ServerRun implements Runnable {

        @Override
        public void run() {
            ServerChat.initializeServer(ServerController.this,PORT);
        }
        public void closeServer()
        {
            ServerChat.closeServer();
            logTA.appendText("Server closed connection!\n");
        }
    }

}
