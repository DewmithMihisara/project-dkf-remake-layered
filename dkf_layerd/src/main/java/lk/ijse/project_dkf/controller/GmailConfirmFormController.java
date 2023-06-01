package lk.ijse.project_dkf.controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.model.BuyerModel;
import lk.ijse.project_dkf.model.UserModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.sql.SQLException;

public class GmailConfirmFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField conEmailTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button finishBtn;
    @FXML
    private TextField otpTxt;
    boolean mail;
    @FXML
    void emailTxtOnAction(ActionEvent event) {
        conEmailTxt.requestFocus();
    }
    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.PASSWORD,root);
    }
    @FXML
    void conEmailTxtOnAction(ActionEvent event) {
        otpTxt.requestFocus();
    }
    @FXML
    void otpTxtOnAction(ActionEvent event) {
        finishBtn.fire();
    }
    @FXML
    void finishBtnOnAction(ActionEvent event) throws IOException {
        mail= inputsValidation.email(emailTxt);
        if (emailTxt.getText().equals(conEmailTxt.getText())){
            if (Integer.parseInt(otpTxt.getText()) == PasswordFormController.otpNum){
                NewAcFormController.user.setUserEmail(emailTxt.getText());
                try {
                    boolean affectedRows = UserModel.addUser(NewAcFormController.user);
                    if (affectedRows){
                        String string="New user add. ("+NewAcFormController.user.getUserName()+")";
                        PopUps.popUps(AlertTypes.CONFORMATION, "User", string);;
                        Navigation.navigation(Rout.LOGIN,root);
                        PasswordFormController.otpNum= 0;
                    }
                } catch (SQLException e) {
                    PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when add user.");
                }
            }else {
                ShakeTextAnimation.ShakeText(otpTxt);
            }
        }else {
            ShakeTextAnimation.ShakeText(conEmailTxt);
        }
    }
}
