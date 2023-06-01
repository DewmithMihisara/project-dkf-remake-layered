package lk.ijse.project_dkf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.model.*;
import lk.ijse.project_dkf.notification.PopUps;
import lk.ijse.project_dkf.util.AlertTypes;
import lk.ijse.project_dkf.util.Navigation;
import lk.ijse.project_dkf.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    @FXML
    private ComboBox<String> clrCmbBx;
    @FXML
    private Text exL_Txt;
    @FXML
    private Text exM_Txt;
    @FXML
    private Text exS_Txt;
    @FXML
    private Text exXL_Txt;
    @FXML
    private Text exXXL_Txt;
    @FXML
    private Text finishedS_Txt;
    @FXML
    private Text finishedL_Txt;
    @FXML
    private Text finishedM_Txt;
    @FXML
    private Text finishedXL_Txt;
    @FXML
    private Text finishedXXL_Txt;
    @FXML
    private ComboBox<String> orderIdCmbBox;
    @FXML
    private Text orderL_Txt;
    @FXML
    private Text orderM_Txt;
    @FXML
    private Text orderS_Txt;
    @FXML
    private Text orderXL_Txt;
    @FXML
    private Text orderXXL_Txt;
    @FXML
    private Text reqL_Txt;
    @FXML
    private Text reqM_Txt;
    @FXML
    private Text reqS_Txt;
    @FXML
    private Text reqXL_Txt;
    @FXML
    private Text reqXXL_Txt;
    int s, m, l, xl, xxl;
    @FXML
    private AnchorPane root;
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        try {
            boolean delete = OrderModel.delete(orderIdCmbBox.getSelectionModel().getSelectedItem());
            if (delete) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Delete", "This order is deleted.");
            }

        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when delete order.");
        }finally {
            loadOrderIds();
        }
    }
    @FXML
    void newOrderBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.NEW_ORDER,root);
    }
    @FXML
    void orderIdOnAction(ActionEvent event) {
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
    @FXML
    void clrComOnAction(ActionEvent event) {
        String oId= orderIdCmbBox.getSelectionModel().getSelectedItem();
        String clId= clrCmbBx.getSelectionModel().getSelectedItem();

        loadOrderQty(oId,clId);
        loadFinishQty(oId,clId);
        loadReqAndExtra(oId,clId);
    }
    private void loadReqAndExtra(String oId, String clr) {
        try {
            OrderRatio orderRatio= LoadSizesModel.ratio(oId,clr);

            if (orderRatio != null && (orderRatio.getSQty()) <= s) {
                reqS_Txt.setText("0");
                reqS_Txt.setFill(Color.GREEN);
                exS_Txt.setText(String.valueOf(s - orderRatio.getSQty()));
            }else {
                assert orderRatio != null;
                reqS_Txt.setText(String.valueOf(orderRatio.getSQty() - s));
                exS_Txt.setText("0");
            }
            if (orderRatio.getMQty() <= m) {
                reqM_Txt.setText("0");
                reqM_Txt.setFill(Color.GREEN);
                exM_Txt.setText(String.valueOf(m - orderRatio.getMQty()));
            }else {
                reqM_Txt.setText(String.valueOf(orderRatio.getMQty() - m));
                exM_Txt.setText("0");
            }
            if (orderRatio.getLQty() <= l) {
                reqL_Txt.setText("0");
                reqL_Txt.setFill(Color.GREEN);
                exL_Txt.setText(String.valueOf(l - orderRatio.getLQty()));
            }else {
                reqL_Txt.setText(String.valueOf(orderRatio.getLQty() - l));
                exL_Txt.setText("0");
            }
            if (orderRatio.getXlQty() <= xl) {
                reqXL_Txt.setText("0");
                reqXL_Txt.setFill(Color.GREEN);
                exXL_Txt.setText(String.valueOf(xl - orderRatio.getXlQty()));
            }else {
                reqXL_Txt.setText(String.valueOf(orderRatio.getXlQty() - xl));
                exXL_Txt.setText("0");
            }
            if (orderRatio.getXxlty() <= xxl) {
                reqXXL_Txt.setText("0");
                reqXXL_Txt.setFill(Color.GREEN);
                exXXL_Txt.setText(String.valueOf(xxl - orderRatio.getXxlty()));
            }else {
                reqXXL_Txt.setText(String.valueOf(orderRatio.getXxlty() - xxl));
                exXXL_Txt.setText("0");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadFinishQty(String oId, String clId) {
        try {
            List<Pack> all = PackingModel.getAll(oId);
            for (Pack pack: all){
                if (pack.getClId().equals(clId)){
                    switch (pack.getSize()) {
                        case "S" -> s += pack.getPackQty();
                        case "M" -> m += pack.getPackQty();
                        case "L" -> l += pack.getPackQty();
                        case "XL" -> xl += pack.getPackQty();
                        case "XXl" -> xxl += pack.getPackQty();
                    }
                }
            }

            finishedS_Txt.setText(String.valueOf(s));
            finishedM_Txt.setText(String.valueOf(m));
            finishedL_Txt.setText(String.valueOf(l));
            finishedXL_Txt.setText(String.valueOf(xl));
            finishedXXL_Txt.setText(String.valueOf(xxl));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    void loadOrderQty(String oId,String clId){
        try {
            OrderRatio orderRatio= LoadSizesModel.ratio(oId,clId);

            orderS_Txt.setText(String.valueOf(orderRatio.getSQty()));
            orderM_Txt.setText(String.valueOf(orderRatio.getMQty()));
            orderL_Txt.setText(String.valueOf(orderRatio.getLQty()));
            orderXL_Txt.setText(String.valueOf(orderRatio.getXlQty()));
            orderXXL_Txt.setText(String.valueOf(orderRatio.getXxlty()));



        } catch (SQLException e) {
            PopUps.popUps(AlertTypes.WARNING, "SQL Warning", "Database error when load values.");
        }
    }
    @FXML
    void shipBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Rout.SHIP,root);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderIds();
    }
    private void loadOrderIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = IdModel.loadOrderIds();
        } catch (SQLException e) {}

        for (String id : ids) {
            obList.add(id);
        }
        orderIdCmbBox.setItems(obList);
    }
}
