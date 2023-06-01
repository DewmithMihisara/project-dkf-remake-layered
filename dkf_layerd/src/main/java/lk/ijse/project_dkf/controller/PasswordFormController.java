package lk.ijse.project_dkf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.model.UserModel;
import lk.ijse.project_dkf.util.Gmail;
import lk.ijse.project_dkf.util.MailTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.util.ResourceBundle;

public class PasswordFormController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button nxtBtn;
    @FXML
    private TextField pwConformTxt;
    @FXML
    private TextField pwTxt;
    @FXML
    private Label char8Lbl;
    @FXML
    private Label uprLbl;
    @FXML
    private Label specialLbl;
    @FXML
    private Label nbLbl;
    private boolean pw, cnfPw;
    public static int otpNum;

    {
        pw = false;
    }
    @FXML
    void pwConformTxtOnAction(ActionEvent event) {
        nxtBtn.fire();
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        cnfPw = inputsValidation.isNullTxt(pwConformTxt);
        String ownerMail;
        if (pw) {
            if (cnfPw && pw) {
                if (pwTxt.getText().equals(pwConformTxt.getText())) {
                    NewAcFormController.user.setPassword(pwTxt.getText());
                    try {
                        ownerMail= UserModel.getOwnerMail();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Thread printThread = new Thread(() -> {
                        otpNum=Gmail.getOtp(ownerMail, MailTypes.NEW_AC);
                    });
                    printThread.start();
                    Navigation.navigation(Rout.GMAIL, root);
                } else {
                    ShakeTextAnimation.ShakeText(pwConformTxt);
                    ShakeTextAnimation.ShakeText(pwTxt);
                }
            }
        } else {
            ShakeTextAnimation.ShakeText(pwTxt);
        }
    }

    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_AC,root);
    }

    @FXML
    void pwTxtOnAction(ActionEvent event) {
        pwConformTxt.requestFocus();
    }

    @FXML
    void pwOnKeyTypeAction(KeyEvent event) {
        boolean cc, capL, nb, sc;
        if (pwTxt.getText().length() >= 8) {
            char8Lbl.setTextFill(Color.GREEN);
            cc = true;
        } else {
            char8Lbl.setTextFill(Color.RED);
            cc = false;
        }
        if (pwTxt.getText().matches(".*[A-Z].*")) {
            uprLbl.setTextFill(Color.GREEN);
            capL = true;
        } else {
            uprLbl.setTextFill(Color.RED);
            capL = false;
        }
        if (pwTxt.getText().matches(".*[0-9].*")) {
            nbLbl.setTextFill(Color.GREEN);
            nb = true;
        } else {
            nbLbl.setTextFill(Color.RED);
            nb = false;
        }
        if (pwTxt.getText().matches(".*[!@#$%^&*()].*")) {
            specialLbl.setTextFill(Color.GREEN);
            sc = true;
        } else {
            specialLbl.setTextFill(Color.RED);
            sc = false;
        }
        if (cc && capL && nb && sc) {
            pw = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (NewAcFormController.user!=null){
            setValues();
        }
    }

    private void setValues() {
        pwTxt.setText(NewAcFormController.user.getPassword());
        pwConformTxt.setText(NewAcFormController.user.getPassword());
    }
}
