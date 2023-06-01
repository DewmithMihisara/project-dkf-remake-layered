package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.animation.SetTime;
import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.model.CutModel;
import lk.ijse.project_dkf.model.IdModel;
import lk.ijse.project_dkf.model.OutputModel;
import lk.ijse.project_dkf.model.PackingModel;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CutInputFormController implements Initializable {
    @FXML
    private Label timeLbl;
    @FXML
    private TableColumn<?, ?> timeColm;
    @FXML
    private TableColumn<?, ?> clIdColm;
    @FXML
    private TableView<CutTM> cutTbl;
    @FXML
    private TableColumn<?, ?> dateColm;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TableColumn<?, ?> sizeColm;
    @FXML
    private TableColumn<?, ?> typeColm;
    @FXML
    private ComboBox<String> clrCmbBx;
    @FXML
    private Text dateTxt;
    @FXML
    private ComboBox<String> orderIdCmbBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField qtyTxt;
    @FXML
    private ComboBox<String> sizeCmbBx;
    @FXML
    private TextField typeTxt;

    boolean clId, size, type, qty;
    {
        clId=false;
        size=false;
        type=false;
        qty=false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        clId=inputsValidation.isNullCmb(clrCmbBx);
        size=inputsValidation.isNullCmb(sizeCmbBx);
        type=inputsValidation.isNullTxt(typeTxt);
        qty=inputsValidation.isNumberOrNull(qtyTxt);

        if(clId && size && type && qty){
            Cut cut = new Cut(
                    orderIdCmbBox.getSelectionModel().getSelectedItem(),
                    clrCmbBx.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dateTxt.getText()),
                    Time.valueOf(timeLbl.getText()),
                    Integer.parseInt(qtyTxt.getText()),
                    typeTxt.getText(),
                    sizeCmbBx.getSelectionModel().getSelectedItem()
            );
            String string= "Cut input of "+cut.getCutID();
            try {
                boolean affectedRows = CutModel.add(cut);
                if (affectedRows) {
                    PopUps.popUps(AlertTypes.CONFORMATION, "Cut Added" ,string);
                }
            } catch (SQLException e) {
                PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when add cut.");
            } finally {
                loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
            }
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        CutTM cutTM = cutTbl.getSelectionModel().getSelectedItem();
        String string=cutTM.getClothID() +" is delete";
        try {
            boolean delete = CutModel.delete(cutTM, orderIdCmbBox.getSelectionModel().getSelectedItem());
            if (delete) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Cut Added" ,string);
            }

        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when delete cut.");
        } finally {
            loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    void materialBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.MATERIAL_IN, pane);
    }
    @FXML
    void orderIdOnAction(ActionEvent event) {
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        loadClotheId();
        clrCmbBx.setDisable(false);
    }
    private void loadClotheId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = IdModel.loadClothId(orderIdCmbBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        clrCmbBx.setItems(obList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSize();
        loadOrderIds();
        setOrderDate();
        setCellValueFactory();
        setTime();
    }
    private void loadSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> clr = new ArrayList<>();
        clr.add("S");
        clr.add("M");
        clr.add("L");
        clr.add("XL");
        clr.add("XXl");

        obList.addAll(clr);
        sizeCmbBx.setItems(obList);
    }
    private void loadOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = IdModel.loadOrderIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }
    private void loadValues(String id) {
        ObservableList<CutTM> cutTMS = FXCollections.observableArrayList();
        try {
            List<Cut> all = CutModel.getAll(id);
            for (Cut cut : all) {
                cutTMS.add(new CutTM(
                        cut.getDate(),
                        cut.getTime(),
                        cut.getClothId(),
                        cut.getSize(),
                        cut.getType(),
                        cut.getCutQty()
                ));
            }
            cutTbl.setItems(cutTMS);
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when load cut-in details.");
        }
    }
    private void setCellValueFactory() {
        dateColm.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColm.setCellValueFactory(new PropertyValueFactory<>("time"));
        clIdColm.setCellValueFactory(new PropertyValueFactory<>("clothID"));
        sizeColm.setCellValueFactory(new PropertyValueFactory<>("size"));
        typeColm.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    private void setOrderDate() {
        dateTxt.setText(String.valueOf(LocalDate.now()));
    }
    void setTime(){
        SetTime.setTime(timeLbl);
    }
}
