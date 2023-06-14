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
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.MaterialBO;
import lk.ijse.project_dkf.dto.MaterialDTO;
import lk.ijse.project_dkf.view.tm.MaterialTM;
import lk.ijse.project_dkf.controller.util.PopUps;
import lk.ijse.project_dkf.controller.util.AlertTypes;
import lk.ijse.project_dkf.controller.util.Navigation;
import lk.ijse.project_dkf.controller.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialInputFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> dateColm;
    @FXML
    private Text dateTxt;
    @FXML
    private TableColumn<?, ?> orderColm;
    @FXML
    private ComboBox<String> orderIdCmbBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TextField qtyTxt;
    @FXML
    private TableView<MaterialTM> tblMetarial;
    @FXML
    private TableColumn<?, ?> timeColm;
    @FXML
    private Label timeLbl;
    @FXML
    private ComboBox<String> typeCmbBx1;
    boolean mid, qty;
    MaterialBO materialBO= BOFactory.getBoFactory().getBO(BOFactory.BO.MATERIAL);

    {
        mid = false;
        qty = false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        mid = inputsValidation.isNullCmb(typeCmbBx1);
        qty = inputsValidation.isNumberOrNull(qtyTxt);

        if (mid && qty) {
            MaterialDTO materialDTO = new MaterialDTO(
                    orderIdCmbBox.getSelectionModel().getSelectedItem(),
                    typeCmbBx1.getSelectionModel().getSelectedItem(),
                    Time.valueOf(timeLbl.getText()),
                    Integer.parseInt(qtyTxt.getText()),
                    Date.valueOf(dateTxt.getText())
            );
            String string= "CutDTO added of orderDTO "+ materialDTO.getOrderID();
            try {
                boolean affectedRows = materialBO.add(materialDTO);
                if (affectedRows) {
                    PopUps.popUps(AlertTypes.CONFORMATION, "CutDTO Added" ,string);
                }
            } catch (SQLException e) {
                PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when add cut.");
                e.printStackTrace();
            } finally {
                loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
            }
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        MaterialTM materialTM = tblMetarial.getSelectionModel().getSelectedItem();
        try {
            boolean delete = materialBO.delete(new MaterialDTO(
                    orderIdCmbBox.getSelectionModel().getSelectedItem(),
                    materialTM.getOid(),
                    materialTM.getTime(),
                    materialTM.getQty(),
                    materialTM.getDate())
            );
            if (delete) {
                PopUps.popUps(AlertTypes.CONFORMATION, "CutDTO Delete" ,"CutDTO is deleted.");
            }

        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when delete cut.");
        } finally {
            loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    void cutInBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.CUT_IN, pane);
    }
    @FXML
    void orderIdOnAction(ActionEvent event) {
        loadMaterials();
        loadValues(orderIdCmbBox.getSelectionModel().getSelectedItem());
    }
    private void loadMaterials() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = materialBO.loadMaterialId(orderIdCmbBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        typeCmbBx1.setItems(obList);
    }
    private void loadValues(String id) {
        ObservableList<MaterialTM> materialTMS = FXCollections.observableArrayList();
        try {
            List<MaterialDTO> all = materialBO.getAll(id);
            for (MaterialDTO materialDTO : all) {
                materialTMS.add(new MaterialTM(
                        materialDTO.getDate(),
                        materialDTO.getTime(),
                        materialDTO.getMid(),
                        materialDTO.getQty()
                ));
            }
            tblMetarial.setItems(materialTMS);
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when load cut.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderIds();
        setOrderDate();
        setTime();
        setCellValueFactory();
    }
    private void loadOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = materialBO.loadOrderIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }
    private void setOrderDate() {
        dateTxt.setText(String.valueOf(LocalDate.now()));
    }
    private void setCellValueFactory() {
        dateColm.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColm.setCellValueFactory(new PropertyValueFactory<>("time"));
        orderColm.setCellValueFactory(new PropertyValueFactory<>("oid"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    void setTime() {
        SetTime.setTime(timeLbl);
    }
}
