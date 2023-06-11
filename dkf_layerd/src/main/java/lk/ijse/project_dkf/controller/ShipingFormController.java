package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.bo.BOFactory;
import lk.ijse.project_dkf.bo.custom.ShippingBO;
import lk.ijse.project_dkf.bo.custom.impl.ShippingBOImpl;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.tm.ShipmentTM;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.validation.inputsValidation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ShipingFormController implements Initializable {
    @FXML
    private Label dateLbl;
    @FXML
    private Label buyerAddressTxt;
    @FXML
    private Label buyerNameTxt;
    @FXML
    private ComboBox<String> clothIdCmbBox;
    @FXML
    private TableColumn<?, ?> clothIdColm;
    @FXML
    private TableColumn<?, ?> descColm;
    @FXML
    private ComboBox<String> orderIdCmbBox;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TextField qtyTxt;
    @FXML
    private TableView<ShipmentTM> shipTbl;
    @FXML
    private ComboBox<String> sizeCmbBx;
    @FXML
    private Label AvalabilityLbl;
    @FXML
    private TableColumn<?, ?> sizeColm;
    public static ObservableList<ShipmentDTO> shipmentDTOS = FXCollections.observableArrayList();
    private ObservableList<ShipmentTM> shipmentTMS = FXCollections.observableArrayList();

    ShippingBO shippingBO= BOFactory.getBoFactory().getBO(BOFactory.BO.SHIPPING);
    boolean cid,sz,qty;
    {
        cid=false;
        sz=false;
        qty=false;
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        cid= inputsValidation.isNullCmb(clothIdCmbBox);
        sz=inputsValidation.isNullCmb(sizeCmbBx);
        qty=inputsValidation.isNumberOrNull(qtyTxt);

        if(cid && sz && qty){
            if(Integer.parseInt(AvalabilityLbl.getText()) >= Integer.parseInt(qtyTxt.getText()) && Integer.parseInt(AvalabilityLbl.getText()) != 0){
                String detail;
                try {
                    detail= shippingBO.searchClothDetail(clothIdCmbBox.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                shipmentDTOS.add(new ShipmentDTO(
                        orderIdCmbBox.getSelectionModel().getSelectedItem(),
                        buyerNameTxt.getText(),
                        buyerAddressTxt.getText(),
                        clothIdCmbBox.getSelectionModel().getSelectedItem(),
                        sizeCmbBx.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(qtyTxt.getText()),
                        Date.valueOf(dateLbl.getText()),
                        detail
                ));
                shipmentTMS.add(new ShipmentTM(
                        shipmentDTOS.get(shipmentDTOS.size()-1).getClid(),
                        shipmentDTOS.get(shipmentDTOS.size()-1).getDesc(),
                        shipmentDTOS.get(shipmentDTOS.size()-1).getSize(),
                        shipmentDTOS.get(shipmentDTOS.size()-1).getQty()
                ));
                shipTbl.setItems(shipmentTMS);
            }else {
                ShakeTextAnimation.ShakeText(qtyTxt);
            }

        }

    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        ShipmentTM selectedItem = shipTbl.getSelectionModel().getSelectedItem();
        String id=selectedItem.getClthId();
        String desc=selectedItem.getDesc();
        String size=selectedItem.getSize();
        int qty=selectedItem.getQty();

        for (int i = 0; i < shipmentTMS.size(); i++) {
            if (shipmentTMS.get(i).getClthId().equals(id)
                    && shipmentTMS.get(i).getDesc().equals(desc)
                    && shipmentTMS.get(i).getSize().equals(size)
                    && shipmentTMS.get(i).getQty()==qty){
                shipmentTMS.remove(i);
            }
        }
        for (int i = 0; i < shipmentDTOS.size(); i++) {
            if (shipmentDTOS.get(i).getClid().equals(id)
                    && shipmentDTOS.get(i).getDesc().equals(desc)
                    && shipmentDTOS.get(i).getSize().equals(size)
                    && shipmentDTOS.get(i).getQty()==qty){
                shipmentDTOS.remove(i);
                break;
            }
        }
    }
    @FXML
    void placeOnAction(ActionEvent event) throws JRException {
        try {
            boolean isPlaced= shippingBO.shipmentPlace(shipmentDTOS);
            PopUps.popUps(AlertTypes.CONFORMATION, "Shipped", "ShipmentDTO is done properly.");
            Thread printThread = new Thread(() -> {
                try {
                    if (isPlaced){
                        InputStream rpt = ShipingFormController.class.getResourceAsStream("/reports/Invoice.jrxml");
                        JasperReport compile =  JasperCompileManager.compileReport(rpt);
                        Map<String,Object> data = new HashMap<>();
                        data.put("name", shipmentDTOS.get(0).getBuyerName());
                        data.put("adrs", shipmentDTOS.get(0).getBuyerAddress());
                        JasperPrint report = JasperFillManager.fillReport(compile,data, DBConnection.getInstance().getConnection());
                        JasperViewer.viewReport(report,false);
                    }
                } catch (JRException | SQLException e){
                    e.printStackTrace();
                }
            });
            printThread.start();
        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when shipping transaction.");
        }
    }
    @FXML
    void orderIdOnAction(ActionEvent event) {
        loadClotheId();
        loadBuyer();
        clothIdCmbBox.setDisable(false);
    }
    @FXML
    void clothIdCmbBoxOnAction(ActionEvent event) {
        loadAvailability();
    }
    @FXML
    void sizeCmbBxOnAction(ActionEvent event) {
        loadAvailability();
    }
    private void loadAvailability() {
        try {
            int available = shippingBO.searchAvailability(clothIdCmbBox.getSelectionModel().getSelectedItem(), sizeCmbBx.getSelectionModel().getSelectedItem());
            AvalabilityLbl.setText(String.valueOf(available));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadBuyer() {
        try {
            BuyerDTO buyerDTO = shippingBO.searchBuyer(orderIdCmbBox.getSelectionModel().getSelectedItem());

            buyerNameTxt.setText(buyerDTO.getBuyerName());
            buyerAddressTxt.setText(buyerDTO.getBuyerAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } {

        }
    }
    private void loadClotheId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = shippingBO.loadClothId(orderIdCmbBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        clothIdCmbBox.setItems(obList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderDate();
        loadSize();
        loadOrderIds();
        setCellValueFactory();
    }
    private void loadSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> clr=new ArrayList<>();
        clr.add("S");
        clr.add("M");
        clr.add("L");
        clr.add("XL");
        clr.add("XXl");
        obList.addAll(clr);
        sizeCmbBx.setItems(obList);
    }
    private void setCellValueFactory() {
        clothIdColm.setCellValueFactory(new PropertyValueFactory<>("clthId"));
        descColm.setCellValueFactory(new PropertyValueFactory<>("desc"));
        sizeColm.setCellValueFactory(new PropertyValueFactory<>("size"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    private void loadOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = shippingBO.loadOrderIds();
        } catch (SQLException e) {}

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }
    private void setOrderDate() {
        dateLbl.setText(String.valueOf(LocalDate.now()));
    }
}
