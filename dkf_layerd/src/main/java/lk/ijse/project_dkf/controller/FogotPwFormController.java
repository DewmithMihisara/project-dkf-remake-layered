package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.ForgotPwBO;
import lk.ijse.project_dkf.bo.custom.impl.ForgotPwBOImpl;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FogotPwFormController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField cnPwTxt;

    @FXML
    private Button doneBtn;

    @FXML
    private TextField newPwTxt;

    @FXML
    private Button okBtn;

    @FXML
    private TextField otpTxt;
    private int otp;
    ForgotPwBO forgotPwBO= BOFactory.getBoFactory().getBO(BOFactory.BO.FORGOT_PW);
    @FXML
    void cnPwTxtOnAction(ActionEvent event) {
        doneBtn.fire();
    }

    @FXML
    void dnBtnOnAction(ActionEvent event) throws IOException {
        if (newPwTxt.getText().equals(cnPwTxt.getText())) {
            try {
                boolean isUpdate = forgotPwBO.updatePw(newPwTxt.getText(), LogInFormController.usrName);
                if (isUpdate) {
                    PopUps.popUps(AlertTypes.CONFORMATION, "Password", "Password is reset properly.");
                    Navigation.navigation(Rout.LOGIN, root);
                } else {
                    PopUps.popUps(AlertTypes.ERROR, "Password", "Password is not update");
                }
            } catch (SQLException e) {
                PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Data base error when update password");
            }
        }
    }

    @FXML
    void newPwTxtOnAction(ActionEvent event) {
        cnPwTxt.requestFocus();
    }

    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.LOGIN, root);
    }

    @FXML
    void okBtnOnAction(ActionEvent event) {
        if (Integer.parseInt(otpTxt.getText()) == otp) {
            newPwTxt.setDisable(false);
            cnPwTxt.setDisable(false);
            doneBtn.setDisable(false);
            newPwTxt.requestFocus();
        } else {
            ShakeTextAnimation.ShakeText(otpTxt);
        }
    }

    @FXML
    void otpTxtOnAction(ActionEvent event) {
        okBtn.fire();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendOtp();
        otpTxt.requestFocus();
    }

    private void sendOtp() {
        String ownerMail;
        try {
            ownerMail = forgotPwBO.getOwnerMail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Thread printThread = new Thread(() -> {
            otp = Gmail.getOtp(ownerMail, MailTypes.FORGOT_PW);

        });
        printThread.start();
    }
}
