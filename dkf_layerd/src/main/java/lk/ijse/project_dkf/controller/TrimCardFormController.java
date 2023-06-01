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
import javafx.stage.Stage;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.TrimCard;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.dto.tm.TrimCardTM;
import lk.ijse.project_dkf.model.OrderRatioModel;
import lk.ijse.project_dkf.model.PlaceOrderModel;
import lk.ijse.project_dkf.model.TrimCardModel;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TrimCardFormController implements Initializable {
    @FXML
    private Button addBtn;
    public static String setOrderId;
    @FXML
    private TableColumn<?, ?> clrColm;
    @FXML
    private TableColumn<?, ?> trimIdColm;
    @FXML
    private Label trimIDTxt;
    @FXML
    private TextField clrTxt;
    @FXML
    private Button dnBtn;
    @FXML
    private TextField materialTxt;
    @FXML
    private Text orderIdTxt;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<?, ?> qtyColm;
    @FXML
    private TextField reqTtlTxt;
    @FXML
    private TableView<TrimCardTM> tblTrimCard;
    @FXML
    private TableColumn<?, ?> typeColm;
    public static ObservableList<TrimCardTM> trimCardObj = FXCollections.observableArrayList();
    boolean material, clr, qty;
    {
        material=false;
        clr=false;
        qty=false;
    }
    @FXML
    void materialTxtOnAction(ActionEvent event) {
        clrTxt.requestFocus();
    }
    @FXML
    void clrTxtOnAction(ActionEvent event) {
        reqTtlTxt.requestFocus();
    }
    @FXML
    void reqTtlTxtOnAction(ActionEvent event) {
        addBtn.fire();
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        material= inputsValidation.isNullTxt(materialTxt);
        clr=inputsValidation.isNullTxt(clrTxt);
        qty=inputsValidation.isNumberOrNull(reqTtlTxt);

        if (material && clr && qty){
            trimCardObj.add(new TrimCardTM(
                    trimIDTxt.getText(),
                    materialTxt.getText(),
                    clrTxt.getText(),
                    Integer.parseInt(reqTtlTxt.getText())
            ));
            tblTrimCard.setItems(trimCardObj);

            String[]strings=trimIDTxt.getText().split("Tr-");
            int id= Integer.parseInt(strings[1]);
            id++;

            String num=String.valueOf(id);
            String txt="Tr-"+num;
            trimIDTxt.setText(txt);

            materialTxt.setText("");
            clrTxt.setText("");
            reqTtlTxt.setText("");
        }
    }
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        TrimCardTM selectedItem = tblTrimCard.getSelectionModel().getSelectedItem();
        String id=selectedItem.getId();
        for (int i = 0; i < trimCardObj.size(); i++) {
            if (trimCardObj.get(i).getId().equals(id)){
                trimCardObj.remove(i);
                break;
            }
        }
    }
    @FXML
    void doneBtnOnAction(ActionEvent event) throws IOException {
        if (trimCardObj.size()==0){
            new Alert(Alert.AlertType.WARNING,
                    "please add Trim card")
                    .show();
        }else {
            try {
                boolean isPlaced= PlaceOrderModel.placeOrder();
                if(isPlaced) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }finally {
                OrderRatioController.addQty=0;
                NewOrderFormController.order=null;
                OrderRatioController.orderRatioTM=FXCollections.observableArrayList();
                TrimCardFormController.trimCardObj=FXCollections.observableArrayList();

                Navigation.navigation(Rout.NEW_ORDER,pane);
            }
        }
    }
    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER_RATIO,pane);
    }
    @FXML
    void declairAllBtnOnAction(ActionEvent event) throws IOException {
        NewOrderFormController.order=null;
        OrderRatioController.orderRatioTM=FXCollections.observableArrayList();
        trimCardObj=FXCollections.observableArrayList();

        Navigation.navigation(Rout.NEW_ORDER,pane);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        setCellValueFactory();
        if (trimCardObj.size()==0){
            generateOrderID();
        }else {
            generateTrimIdByArray();
        }

        if (trimCardObj !=null){
            loadValues();
        }
    }
    private void generateTrimIdByArray() {
        TrimCardTM trimCardTM=trimCardObj.get(trimCardObj.size()-1);
        String string=trimCardTM.getId();
        String [] ar=string.split("Tr-");
        int id= Integer.parseInt(ar[1]);
        id++;

        String num=String.valueOf(id);
        String txt="Cl-"+num;
        trimIDTxt.setText(txt);
    }
    private void loadValues() {
        tblTrimCard.setItems(trimCardObj);
    }
    private void setCellValueFactory() {
        trimIdColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        clrColm.setCellValueFactory(new PropertyValueFactory<>("clr"));
        typeColm.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColm.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    private void setValues() {
        orderIdTxt.setText(NewOrderFormController.order.getOrderId());
    }
    private void generateOrderID() {
        try {
            String id = TrimCardModel.getNextTrimID();
            trimIDTxt.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
