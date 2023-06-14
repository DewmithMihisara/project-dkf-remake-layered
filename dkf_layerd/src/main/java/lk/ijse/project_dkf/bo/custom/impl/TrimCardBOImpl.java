package lk.ijse.project_dkf.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.bo.custom.TrimCardBO;
import lk.ijse.project_dkf.controller.NewOrderFormController;
import lk.ijse.project_dkf.controller.OrderRatioController;
import lk.ijse.project_dkf.controller.TrimCardFormController;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.TrimCardDAO;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.entity.Order;
import lk.ijse.project_dkf.view.tm.TrimCardTM;

import java.sql.Connection;
import java.sql.SQLException;

public class TrimCardBOImpl implements TrimCardBO {
    OrdersDAO ordersDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    OrderRatioDAO orderRatioDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    TrimCardDAO trimCardDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.TRIM_CARD);
    public boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException {
        return trimCardDAO.addTrimCard(trimCardObj,orderId);
    }
    public String getNextTrimID() throws SQLException {
        return trimCardDAO.generateNewID();
    }
    public boolean placeOrder() throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            OrderDTO oDTO=NewOrderFormController.orderDTO;
            boolean isOrderSaved = this.ordersDAO.add(new Order(oDTO.getOrderId(),oDTO.getCompId(),oDTO.getDline(),oDTO.getTtlQty(),oDTO.getDailyOut(),oDTO.getPayment(),oDTO.getOrderDate()));
            System.out.println(isOrderSaved+" order");
            if (isOrderSaved) {
                boolean isOrderRatioSaved = this.orderRatioDAO.addRatio(OrderRatioController.orderRatioTM, NewOrderFormController.orderDTO.getOrderId());
                System.out.println(isOrderRatioSaved+" ratio");
                if (isOrderRatioSaved) {
                    boolean isTrimCardSave = addTrimCard(TrimCardFormController.trimCardObj, NewOrderFormController.orderDTO.getOrderId());
                    System.out.println(isTrimCardSave+ " trim");
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
