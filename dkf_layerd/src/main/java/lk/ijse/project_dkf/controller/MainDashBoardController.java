package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.MainDashBoardBO;
import lk.ijse.project_dkf.controller.util.*;
import lk.ijse.project_dkf.voiceAssistant.Assistant;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static lk.ijse.project_dkf.controller.util.Rout.*;

public class MainDashBoardController implements Initializable {
    @FXML
    private Label assLbl;
    @FXML
    private Button retailBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button bkBtn;
    @FXML
    private Label timeTxt;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView settingImg;
    @FXML
    private Button employeeBtn;
    @FXML
    private Button assBtn;
    Assistant assistant;
    String command;
    MainDashBoardBO mainDashBoardBO= BOFactory.getBoFactory().getBO(BOFactory.BO.MAIN_DASH_BOARD);
    @FXML
    void assistantOnAction(ActionEvent event) throws IOException, JavaLayerException {
        Thread audio=new Thread(()->{
            File audioFile = new File("src/main/resources/assistantResources/voice/aiLisining.mp3");
            FileInputStream audioStream = null;
            try {
                audioStream = new FileInputStream(audioFile);
                Player player = new Player(audioStream);
                player.play();
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        });
        audio.start();
        assistant = new Assistant();
        command = Assistant.assistant();

        if (command != null) {
            audio=new Thread(()->{
                File audioFile = new File("src/main/resources/assistantResources/voice/aiCantCatch.mp3");
                FileInputStream audioStream = null;
                try {
                    audioStream = new FileInputStream(audioFile);
                    Player player = new Player(audioStream);
                    player.play();
                } catch (FileNotFoundException | JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            audio.start();
        } else if (command.contains("SETTINGS")) {
            Navigation.navigation(Rout.USER_SETTINGS, midleStage);
        } else if (command.contains("ORDER")) {
            Navigation.navigation(ORDER, root);
        } else if (command.contains("NEW ORDER")) {
            NewWindowNavigation.windowNavi(NEW_ORDER);
        } else if (command.contains("INPUT")) {
            NewWindowNavigation.windowNavi(CUT_IN);
        } else if (command.contains("OUTPUT")) {
            NewWindowNavigation.windowNavi(OUTPUT);
        } else if (command.contains("PACKING")) {
            NewWindowNavigation.windowNavi(PAKING);
        } else {
            assLbl.setText("No command Found, Bye!");
        }
    }

    @FXML
    void testBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(DASHBOARD, root);
    }

    @FXML
    void retailBtnOnAction(ActionEvent event) {
        new animatefx.animation.Shake(retailBtn).play();
        PopUps.popUps(AlertTypes.ERROR, "Under Development", "This option is in under development. \nExpect this feature in a future update.");
    }

    @FXML
    void buyerBtnOnAction(ActionEvent event) throws IOException {
        bkBtn.setVisible(true);
        Navigation.navigation(BUYER, midleStage);
    }

    @FXML
    void settingMouseEnterOnAction(MouseEvent event) {
        new animatefx.animation.RotateIn(settingImg).play();
    }

    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(MAIN_DASHBOARD, root);
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) {
        new animatefx.animation.Shake(employeeBtn).play();
        PopUps.popUps(AlertTypes.ERROR, "Under Development", "This option is in under development. \nExpect this feature in a future update.");
    }

    @FXML
    void logOutBtnOnAction(ActionEvent event) throws IOException {
        LogInFormController.logHistoryDTO.setLogOut(LocalDateTime.now());
        try {
            mainDashBoardBO.saveLogHistory(LogInFormController.logHistoryDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LogInFormController.logHistoryDTO = null;
        Navigation.navigation(LOGIN, root);
    }

    @FXML
    void settingBtnOnActon(ActionEvent event) throws IOException {
        bkBtn.setVisible(true);
        Navigation.navigation(USER_SETTINGS, midleStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeLbl();

    }

    private void setTimeLbl() {
        SetTime.setTime(timeTxt);
    }
}
