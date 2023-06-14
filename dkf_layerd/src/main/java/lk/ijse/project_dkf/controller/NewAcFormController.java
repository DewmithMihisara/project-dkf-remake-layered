package lk.ijse.project_dkf.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.controller.util.PopUps;
import lk.ijse.project_dkf.controller.util.AlertTypes;
import lk.ijse.project_dkf.controller.util.Navigation;
import lk.ijse.project_dkf.controller.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAcFormController implements Initializable {
    public static UserDTO userDTO;
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
                PopUps.popUps(AlertTypes.INFORMATION, "Attention", "This userDTO name can not add.");
                jobTxt.setText("");
                ShakeTextAnimation.ShakeText(jobTxt);
            }else {
                userDTO =new UserDTO();
                userDTO.setUserName(usrTxt.getText());
                userDTO.setAddress(addressTxt.getText());
                userDTO.setContact(PhoneTxt.getText());
                userDTO.setPosition(jobTxt.getText());

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
        userDTO =null;
        Navigation.navigation(Rout.LOGIN,root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (userDTO != null){
            loadValues();
        }
    }

    private void loadValues() {
        usrTxt.setText(userDTO.getUserName());
        jobTxt.setText(userDTO.getPosition());
        PhoneTxt.setText(userDTO.getContact());
        addressTxt.setText(userDTO.getAddress());
    }
}
