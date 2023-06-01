package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.controller.NewOrderFormController;
import lk.ijse.project_dkf.controller.OrderRatioController;
import lk.ijse.project_dkf.controller.TrimCardFormController;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.Shipment;

import java.sql.Connection;
import java.sql.SQLException;

public class ShippinPlaceModel {
    public static boolean shipmentPlace(ObservableList<Shipment> shipments) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isStockUpdate = StockModel.update(shipments);
            if (isStockUpdate){
                boolean isShipmentAdd= ShipModel.add(shipments);
                if (isShipmentAdd){
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
