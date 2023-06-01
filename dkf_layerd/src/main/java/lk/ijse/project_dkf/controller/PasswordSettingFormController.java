package lk.ijse.project_dkf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.model.UserModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordSettingFormController {
    @FXML
    private Label char8Lbl;
    @FXML
    private Label nbLbl;
    @FXML
    private Label specialLbl;
    @FXML
    private Label uprLbl;
    @FXML
    private AnchorPane midleStage;
    @FXML
    private TextField confrmTxt;

    @FXML
    private Button dnBtn;

    @FXML
    private TextField newPwTxt;

    @FXML
    private TextField oldPwTxt;
    boolean pw,oldPw,cnPw;

    {
        pw=false;
        oldPw=false;
        cnPw=false;
    }

    @FXML
    void confrmTxtOnAction(ActionEvent event) {
        dnBtn.fire();
    }

    @FXML
    void dnBtnOnAction(ActionEvent event) {
        if (LogInFormController.user.getPassword().equals(oldPwTxt.getText())){
            oldPw=true;
            System.out.println("ok");
        }else {
            ShakeTextAnimation.ShakeText(oldPwTxt);
        }
        if (newPwTxt.getText().equals(confrmTxt.getText())){
            cnPw=true;
            if (pw && oldPw && cnPw){
                LogInFormController.user.setPassword(newPwTxt.getText());
                try {
                    boolean isUpdate=UserModel.update(LogInFormController.user);
                    if (isUpdate){
                        PopUps.popUps(AlertTypes.CONFORMATION, "Update User", "Password update properly.");
                        oldPwTxt.setText("");
                        newPwTxt.setText("");
                        confrmTxt.setText("");
                    }
                } catch (SQLException e) {
                    PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when update user.");
                }
            }
        }else {
            ShakeTextAnimation.ShakeText(confrmTxt);
        }
    }

    @FXML
    void newPwTxtOnAction(ActionEvent event) {
        confrmTxt.requestFocus();
    }

    @FXML
    void oldPwTxtOnAction(ActionEvent event) {
        newPwTxt.requestFocus();
    }

    @FXML
    void profileBtn(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.USER_SETTINGS,midleStage);
    }
    @FXML
    void pwOnKeyTypeAction(KeyEvent event) {
        boolean cc, capL, nb, sc;
        if (newPwTxt.getText().length() >= 8) {
            char8Lbl.setTextFill(Color.GREEN);
            cc = true;
        } else {
            char8Lbl.setTextFill(Color.RED);
            cc = false;
        }
        if (newPwTxt.getText().matches(".*[A-Z].*")) {
            uprLbl.setTextFill(Color.GREEN);
            capL = true;
        } else {
            uprLbl.setTextFill(Color.RED);
            capL = false;
        }
        if (newPwTxt.getText().matches(".*[0-9].*")) {
            nbLbl.setTextFill(Color.GREEN);
            nb = true;
        } else {
            nbLbl.setTextFill(Color.RED);
            nb = false;
        }
        if (newPwTxt.getText().matches(".*[!@#$%^&*()].*")) {
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

    @FXML
    void pwBtn(ActionEvent event) {

    }
}
