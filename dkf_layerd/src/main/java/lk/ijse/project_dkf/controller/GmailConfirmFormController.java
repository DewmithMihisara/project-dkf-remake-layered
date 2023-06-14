package lk.ijse.project_dkf.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.GmailConfirmBO;
import lk.ijse.project_dkf.controller.util.PopUps;
import lk.ijse.project_dkf.controller.util.AlertTypes;
import lk.ijse.project_dkf.controller.util.Navigation;
import lk.ijse.project_dkf.controller.util.Rout;
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
    GmailConfirmBO gmailConfirmBO= BOFactory.getBoFactory().getBO(BOFactory.BO.GMAIL_CONFIRM);
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
                NewAcFormController.userDTO.setUserEmail(emailTxt.getText());
                try {
                    boolean affectedRows = gmailConfirmBO.addUser(NewAcFormController.userDTO);
                    if (affectedRows){
                        String string="New userDTO add. ("+NewAcFormController.userDTO.getUserName()+")";
                        PopUps.popUps(AlertTypes.CONFORMATION, "UserDTO", string);;
                        Navigation.navigation(Rout.LOGIN,root);
                        PasswordFormController.otpNum= 0;
                    }
                } catch (SQLException e) {
                    PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when add userDTO.");
                }
            }else {
                ShakeTextAnimation.ShakeText(otpTxt);
            }
        }else {
            ShakeTextAnimation.ShakeText(conEmailTxt);
        }
    }
}
