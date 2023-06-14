package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.LogInBO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.controller.util.PopUps;
import lk.ijse.project_dkf.controller.util.AlertTypes;
import lk.ijse.project_dkf.controller.util.Navigation;
import lk.ijse.project_dkf.controller.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogInFormController {
    @FXML
    private Button frgtPwBtn;
    @FXML
    private TextField pwTxt;
    @FXML
    private AnchorPane root;
    @FXML
    private Button sgnMainBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private TextField usrTxt;

    public static UserDTO userDTO;
    public static LogHistoryDTO logHistoryDTO;
    public static String usrName;
    boolean uName,pw;
    LogInBO logInBO= BOFactory.getBoFactory().getBO(BOFactory.BO.LOG_IN);
    @FXML
    void frgtPwBtnOnActon(ActionEvent event) throws IOException {
        uName=inputsValidation.isNullTxt(usrTxt);
        try {
            UserDTO isUsr= logInBO.isCorrect(usrTxt.getText());
            if (isUsr.getUserName() != null){
                usrName=usrTxt.getText();
                Navigation.navigation(Rout.FORGOT_PASS,root);
            }else {
                PopUps.popUps(AlertTypes.ERROR,"UserDTO Name","Input userDTO name is wrong.\n Try with correct one.");
                ShakeTextAnimation.ShakeText(usrTxt);
            }
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING,"SQL Warning","Database error when search userDTO.");
        }
    }

    @FXML
    void sgnMainBtnOnAction(ActionEvent event) throws IOException {
        pw= inputsValidation.isNullTxt(pwTxt);
        uName=inputsValidation.isNullTxt(usrTxt);

        if (pw && uName){
            try {
                userDTO = logInBO.isCorrect(usrTxt.getText());
                if (userDTO==null){
                    System.out.println("hello");
                }
                if (userDTO.getPassword().equals(pwTxt.getText())) {
                    Navigation.navigation(Rout.MAIN_DASHBOARD, root);
                    logHistoryDTO =new LogHistoryDTO();
                    logHistoryDTO.setUsrName(usrTxt.getText());
                    logHistoryDTO.setLogIn(LocalDateTime.now());
                } else {
                    PopUps.popUps(AlertTypes.ERROR, "Password is Wrong", "Your password is wrong. Try again");
                    pwTxt.clear();
                }
            } catch (Exception e) {
                PopUps.popUps(AlertTypes.ERROR, "User name is Wrong", "Your userDTO-name is wrong. Try again");
                usrTxt.clear();
                pwTxt.clear();
                e.printStackTrace();
            }
        }
    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_AC, root);
    }

    @FXML
    void pwOnAction(ActionEvent event) {
        sgnMainBtn.fire();
    }

    @FXML
    void usrOnAction(ActionEvent event) {
        pwTxt.requestFocus();
    }
}
