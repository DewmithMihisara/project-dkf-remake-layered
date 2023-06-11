package lk.ijse.project_dkf.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

@Getter
public class DashboardFormController implements Initializable {
    @FXML
    private Button bkBtn;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private Button logOutBtn;
    @FXML
    private AnchorPane root;
    @FXML
    private Button userBtn;
    @FXML
    private Button orderBtn;
    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.LOGIN, root);
    }
    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.USER_SETTINGS, midleStage);
    }
    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.MAIN_DASHBOARD, root);
    }
    @FXML
    void orderBtnOnAction(ActionEvent event) {
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            try {
                Navigation.navigation(Rout.ORDER, midleStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        thread.start();
    }
    @FXML
    void outputBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.OUTPUT, midleStage);
    }
    @FXML
    void packingBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.PAKING, midleStage);
    }
    @FXML
    void inputBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN, midleStage);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderBtn.fire();
    }

}
