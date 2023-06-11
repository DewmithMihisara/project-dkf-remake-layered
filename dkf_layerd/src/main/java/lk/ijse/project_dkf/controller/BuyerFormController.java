package lk.ijse.project_dkf.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.animation.defueltText;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.BuyerBO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.tm.BuyerTM;
import lk.ijse.project_dkf.bo.custom.impl.BuyerBOImpl;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
public class BuyerFormController implements Initializable {
    @FXML
    private AnchorPane midleStage;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableView tblBuyer;
    @FXML
    private Label buyerIdTxt;
    @FXML
    private TextField BuyerCnTxt;
    @FXML
    private TextField buyerAddTxt;
    @FXML
    private TextField buyerNameTxt;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button addBtn;
    boolean name, address, cuntact;
    BuyerBO buyerBO= BOFactory.getBoFactory().getBO(BOFactory.BO.BUYER);

    {
        name = false;
        address = false;
        cuntact = false;
    }
    @FXML
    void buyerNameTxtOnAction(ActionEvent event) {
        buyerAddTxt.requestFocus();
    }
    @FXML
    void buyerAddTxtOnAction(ActionEvent event) {
        BuyerCnTxt.requestFocus();
    }
    @FXML
    void BuyerCnTxtOnAction(ActionEvent event) {
        addBtn.fire();
    }
    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        name = inputsValidation.isNullTxt(buyerNameTxt);
        address = inputsValidation.isNullTxt(buyerAddTxt);
        cuntact = inputsValidation.isNullTxt(BuyerCnTxt);

        if (name && address && cuntact) {
            BuyerDTO buyerDTO = new BuyerDTO(buyerIdTxt.getText(), buyerNameTxt.getText(), BuyerCnTxt.getText(), buyerAddTxt.getText());
            String text="Buyer "+buyerNameTxt.getText()+" added.";
            try {
                boolean affectedRows = buyerBO.addBuyer(buyerDTO);
                tblBuyer.refresh();
                if (affectedRows) {
                    generateOrderID();
                    buyerNameTxt.clear();
                    buyerAddTxt.clear();
                    BuyerCnTxt.clear();

                    PopUps.popUps(AlertTypes.CONFORMATION, "Add Buyer", text);
                }
            } catch (SQLException e) {
                PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when add buyer.");
                e.printStackTrace();
            } finally {
                Navigation.navigation(Rout.BUYER, midleStage);
            }
        }

    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) throws IOException {
        BuyerTM buyerTM = (BuyerTM) tblBuyer.getSelectionModel().getSelectedItem();
        String text="Buyer "+buyerTM.getName()+" delete.";
        try {
            boolean delete = buyerBO.deleteBuyer(new BuyerDTO(buyerTM.getId(),buyerTM.getName(),buyerTM.getCn(),buyerTM.getAddress()));
            if (delete) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Delete Buyer", text);
            }
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when delete buyer.");
            e.printStackTrace();
        } finally {
            Navigation.navigation(Rout.BUYER,midleStage);
        }
    }
    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        BuyerTM tm = (BuyerTM) tblBuyer.getSelectionModel().getSelectedItem();

        if (btnUpdate.getText().equals("Select")) {
            buyerIdTxt.setText(tm.getId());
            buyerAddTxt.setText(tm.getAddress());
            buyerNameTxt.setText(tm.getName());
            BuyerCnTxt.setText(tm.getCn());

            btnUpdate.setText("Update");
            addBtn.setDisable(true);

        } else if (btnUpdate.getText().equals("Update")) {
            name = inputsValidation.isNullTxt(buyerNameTxt);
            address = inputsValidation.isNullTxt(buyerAddTxt);
            cuntact = inputsValidation.isNullTxt(BuyerCnTxt);

            if (name && address && cuntact) {
                BuyerDTO buyerDTO = BuyerDTO.builder()
                        .buyerId(buyerIdTxt.getText())
                        .buyerName(buyerNameTxt.getText())
                        .buyerCn(BuyerCnTxt.getText())
                        .buyerAddress(buyerAddTxt.getText())
                        .build();

                try {
                    boolean update = buyerBO.updateBuyer(buyerDTO);
                    if (update) {
                        String text="Buyer "+ buyerDTO.getBuyerName()+" update";
                        PopUps.popUps(AlertTypes.CONFORMATION, "Update Buyer", text);
                    }
                } catch (SQLException e) {
                    PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when update buyer.");
                    e.printStackTrace();
                } finally {
                    btnUpdate.setText("Select");
                    addBtn.setDisable(false);
                    Navigation.navigation(Rout.BUYER, midleStage);
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCelValueFactory();
        setValues();
        generateOrderID();
    }
    private void generateOrderID() {
        try {
            String id = buyerBO.getNextOrderID();
            buyerIdTxt.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setValues() {
        ObservableList<BuyerTM> object = FXCollections.observableArrayList();
        try {
            List<BuyerDTO> all = buyerBO.getAllBuyers();
            for (BuyerDTO buyerDTO : all) {
                object.add(new BuyerTM(
                        buyerDTO.getBuyerId(),
                        buyerDTO.getBuyerName(),
                        buyerDTO.getBuyerCn(),
                        buyerDTO.getBuyerAddress()
                ));
            }
            tblBuyer.setItems(object);
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when load buyers.");
        }
    }
    private void setCelValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("cn"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
