package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.SettingsBO;
import lk.ijse.project_dkf.bo.custom.impl.SettingsBOImpl;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SettingFormController implements Initializable {
    @FXML
    private TextField addresseditProfileTxt;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField eMailTxt;

    @FXML
    private TextField editprofileContactTxt;

    @FXML
    private TableView<LogHistoryDTO> logHistoryTbl;

    @FXML
    private TableColumn<?, ?> logInTimeClm;

    @FXML
    private TableColumn<?, ?> logOutTimeClm;

    @FXML
    private AnchorPane midleStage;

    @FXML
    private Button updateProfileBtn;

    @FXML
    private Text usrtxtField;
    boolean mail, phn,add;
    SettingsBO settingsBO= BOFactory.getBoFactory().getBO(BOFactory.BO.SETTINGS);

    {
        mail=false;
        phn=false;
        add=false;
    }

    @FXML
    void addresseditProfileTxtOnAction(ActionEvent event) {
        updateProfileBtn.fire();
    }

    @FXML
    void eMailTxtOnAction(ActionEvent event) {
        editprofileContactTxt.requestFocus();
    }

    @FXML
    void editprofileContactTxtOnAction(ActionEvent event) {
        addresseditProfileTxt.requestFocus();
    }

    @FXML
    void passwordBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.PASSWORD_SETTING,midleStage);
    }

    @FXML
    void printBtnOnAction(ActionEvent event) {
            Thread printThread = new Thread(() -> {
                try {
                    boolean isTrue = settingsBO.isHave();
                    if (isTrue){
                        InputStream rpt = ShipingFormController.class.getResourceAsStream("/reports/logHistory.jrxml");
                        JasperReport compile =  JasperCompileManager.compileReport(rpt);
                        Map<String,Object> data = new HashMap<>();
                        JasperPrint report = JasperFillManager.fillReport(compile,data, DBConnection.getInstance().getConnection());
                        JasperViewer.viewReport(report,false);
                    }
                } catch (JRException | SQLException e){
                    e.printStackTrace();
                }
            });
            printThread.start();
    }

    @FXML
    void updateProfileBtnOnAction(ActionEvent event) {
        mail= inputsValidation.isNullTxt(eMailTxt);
        phn=inputsValidation.isNumberOrNull(editprofileContactTxt);
        add=inputsValidation.isNullTxt(addresseditProfileTxt);

        if (mail && phn && add){
            LogInFormController.userDTO.setUserEmail(eMailTxt.getText());
            LogInFormController.userDTO.setContact(editprofileContactTxt.getText());
            LogInFormController.userDTO.setAddress(addresseditProfileTxt.getText());

            try {
                boolean isUpdate=settingsBO.update(LogInFormController.userDTO);
                if (isUpdate){
                    PopUps.popUps(AlertTypes.CONFORMATION, "Update UserDTO", "UserDTO update properly.");
                    editprofileContactTxt.setText("");
                    editprofileContactTxt.setText("");
                    addresseditProfileTxt.setText("");
                }
            } catch (SQLException e) {
                PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when update userDTO.");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        setCelValueFactory();
        setValuesOfTxt();
    }
    void setValuesOfTxt(){
        usrtxtField.setText(LogInFormController.userDTO.getUserName());
        eMailTxt.setText(LogInFormController.userDTO.getUserEmail());
        editprofileContactTxt.setText(LogInFormController.userDTO.getContact());
        addresseditProfileTxt.setText(LogInFormController.userDTO.getAddress());
    }
    private void setCelValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("usrName"));
        logInTimeClm.setCellValueFactory(new PropertyValueFactory<>("logIn"));
        logOutTimeClm.setCellValueFactory(new PropertyValueFactory<>("logOut"));
    }
    private void setValues() {
        ObservableList<LogHistoryDTO> object = FXCollections.observableArrayList();
        try {
            List<LogHistoryDTO> all = settingsBO.getAll();
            for (LogHistoryDTO log : all) {
                object.add(new LogHistoryDTO(
                        log.getUsrName(),
                        log.getLogIn(),
                        log.getLogOut()
                ));
            }
            logHistoryTbl.setItems(object);
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when load buyers.");
        }
    }
}
