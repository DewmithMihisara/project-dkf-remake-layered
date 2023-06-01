package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.controller.NewOrderFormController;
import lk.ijse.project_dkf.controller.OrderRatioController;
import lk.ijse.project_dkf.controller.TrimCardFormController;
import lk.ijse.project_dkf.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderModel {
    public static boolean placeOrder() throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isOrderSaved = OrderModel.addOrder(NewOrderFormController.order);
            if (isOrderSaved) {
                boolean isOrderRatioSaved = OrderRatioModel.addRatio(OrderRatioController.orderRatioTM, NewOrderFormController.order.getOrderId());
                if (isOrderRatioSaved) {
                    boolean isTrimCardSave = TrimCardModel.addTrimCard(TrimCardFormController.trimCardObj, NewOrderFormController.order.getOrderId());
                    if (isTrimCardSave) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }

}
