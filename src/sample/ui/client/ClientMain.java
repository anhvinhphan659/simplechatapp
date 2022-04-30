package sample.ui.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientMain extends Application {

    public static Stage clientStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("client_main.fxml"));
        Parent root=loader.load();
        clientStage=primaryStage;
        primaryStage.setTitle("Server Chat App");
        Scene scene=new Scene(root, 600  , 400);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        primaryStage.show();
    }

    public static void changeToChatUI() throws Exception
    {
        FXMLLoader loader=new FXMLLoader(ClientMain.class.getResource("client_chat.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root,600,600);
        clientStage.setScene(scene);
    }


}
