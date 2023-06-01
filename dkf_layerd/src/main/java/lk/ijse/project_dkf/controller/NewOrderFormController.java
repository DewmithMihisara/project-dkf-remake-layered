package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.animation.ShakeTextAnimation;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.Order;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.model.*;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.NewWindowNavigation;
import lk.ijse.project_dkf.util.Rout;
import lk.ijse.project_dkf.validation.inputsValidation;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Getter
public class NewOrderFormController implements Initializable {
    @FXML
    private Button saveBtn;
    @FXML
    private Button orderBtn;
    @FXML
    private Button trimCardBtn;
    @FXML
    private Label oIdLbl;
    @FXML
    private ComboBox<String> companyCmbBox;
    @FXML
    private TextField companyNameTxt;
    @FXML
    private TextField daylyOutTxt;
    @FXML
    private DatePicker dedlineDate;
    @FXML
    private Text orderDateTxt;
    @FXML
    private TextField paymentTermTxt;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField ttlQtyTxt;
    boolean cmpId, ttl, daily, pay, dedline;
    public static Order order;
    @FXML
    void paymentTermTxtOnAction(ActionEvent event) {
        ttlQtyTxt.requestFocus();
    }
    @FXML
    void ttlQtyTxtOnAction(ActionEvent event) {
        daylyOutTxt.requestFocus();
    }
    @FXML
    void daylyOutTxtOnAction(ActionEvent event) {
        dedlineDate.requestFocus();
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        cmpId = inputsValidation.isNullCmb(companyCmbBox);
        ttl = inputsValidation.isNumberOrNull(ttlQtyTxt);
        daily=inputsValidation.isNumberOrNull(daylyOutTxt);
        pay=inputsValidation.isNullTxt(paymentTermTxt);
        dedline=inputsValidation.isNullDate(dedlineDate , Date.valueOf(orderDateTxt.getText()));

        if (cmpId && ttl && daily && pay && dedline) {
            if (Integer.parseInt(ttlQtyTxt.getText()) > Integer.parseInt(daylyOutTxt.getText())) {
                if (Date.valueOf(dedlineDate.getValue()).after(Date.valueOf(orderDateTxt.getText()))) {
                    order=new Order(
                            oIdLbl.getText(),
                            companyCmbBox.getSelectionModel().getSelectedItem(),
                            Date.valueOf(dedlineDate.getValue()),
                            Integer.parseInt(ttlQtyTxt.getText()),
                            Integer.parseInt(daylyOutTxt.getText()),
                            paymentTermTxt.getText(),
                            Date.valueOf(orderDateTxt.getText())
                            );

                    Navigation.navigation(Rout.ORDER_RATIO,root);
                }else {
                    ShakeTextAnimation.ShakeText(dedlineDate);
                }
            } else {
                ShakeTextAnimation.ShakeText(daylyOutTxt);
            }
        }
    }
    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.ORDER, root);
    }
    @FXML
    void newBuyerBtnOnAction(ActionEvent event) throws IOException {
        NewWindowNavigation.windowNavi(Rout.BUYER);
    }
    @FXML
    void companyCmbOnAction(ActionEvent event) {
        loadCompanyName();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCompanyIds();
        setOrderDate();
        generateOrderID();
        if (order != null){
            loadData();
        }

    }
    private void loadData() {

        oIdLbl.setText(order.getOrderId());
        companyCmbBox.setValue(order.getCompId());
        paymentTermTxt.setText(order.getPayment());
        loadCompanyName();
        ttlQtyTxt.setText(String.valueOf(order.getTtlQty()));
        daylyOutTxt.setText(String.valueOf(order.getDailyOut()));
        dedlineDate.setValue(order.getDline().toLocalDate());
    }
    private void setOrderDate() {
        orderDateTxt.setText(String.valueOf(LocalDate.now()));
    }
    private void loadCompanyIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = NewOrderModel.loadIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String id : ids) {
            obList.add(id);
        }
        companyCmbBox.setItems(obList);
    }
    private void loadCompanyName(){
        String id = (String) companyCmbBox.getValue();

        Buyer buyer = null;
        try {
            buyer = NewOrderModel.searchById(id);
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Sql Error!").show();
        }
        companyNameTxt.setText(buyer.getBuyerName());
    }
    private void generateOrderID() {
        try {
            String id = OrderModel.getNextOrderID();
            oIdLbl.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
