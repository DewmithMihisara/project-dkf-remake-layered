package lk.ijse.project_dkf.controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.model.LogInModel;
import lk.ijse.project_dkf.model.NewACModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewAcFormController implements Initializable {
    public static User user;
    @FXML
    private Button nxtBtn;
    @FXML
    private TextField PhoneTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField jobTxt;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField usrTxt;
    boolean usr,adr,phn,jb_role;
    @FXML
    void PhoneTxtOnAction(ActionEvent event) {
        addressTxt.requestFocus();
    }
    @FXML
    void bkBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.LOGIN,root);
    }
    @FXML
    void addressTxtOnAction(ActionEvent event) {
        nxtBtn.fire();
    }
    @FXML
    void jobTxtOnAction(ActionEvent event) {
        PhoneTxt.requestFocus();
    }
    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        usr= inputsValidation.isNullTxt(usrTxt);
        adr=inputsValidation.isNullTxt(addressTxt);
        phn=inputsValidation.isNumberOrNull(PhoneTxt);
        jb_role=inputsValidation.isNullTxt(jobTxt);

        if (usr && adr && phn && jb_role){
            if (jobTxt.getText().equals("owner")){
                PopUps.popUps(AlertTypes.INFORMATION, "Attention", "This user name can not add.");
                jobTxt.setText("");
                ShakeTextAnimation.ShakeText(jobTxt);
            }else {
                user=new User();
                user.setUserName(usrTxt.getText());
                user.setAddress(addressTxt.getText());
                user.setContact(PhoneTxt.getText());
                user.setPosition(jobTxt.getText());

                Navigation.navigation(Rout.PASSWORD,root);
            }
        }
    }
    @FXML
    void usrTxtOnAction(ActionEvent event) {
        jobTxt.requestFocus();
    }
    @FXML
    void signInBtnOnActon(ActionEvent event) throws IOException {
        user=null;
        Navigation.navigation(Rout.LOGIN,root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null){
            loadValues();
        }
    }

    private void loadValues() {
        usrTxt.setText(user.getUserName());
        jobTxt.setText(user.getPosition());
        PhoneTxt.setText(user.getContact());
        addressTxt.setText(user.getAddress());
    }
}
