package sample.controller.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ui.client.ClientMain;

public class ClientMainController {
    @FXML
    private Button registerBtn;
    @FXML
    private ComboBox serverCB;
    @FXML
    private TextField usernameTF;
    @FXML
    private MenuItem modifyServerMI;
    @FXML
    private MenuItem addServerMI;
    @FXML
    private MenuItem closeMI;
    @FXML
    private MenuItem deleteServerMI;

    @FXML
    public void onRegisterClick()
    {
        try {
            ClientMain.changeToChatUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeApp()
    {
        Stage stage=(Stage)registerBtn.getScene().getWindow();
        stage.close();
    }
}
