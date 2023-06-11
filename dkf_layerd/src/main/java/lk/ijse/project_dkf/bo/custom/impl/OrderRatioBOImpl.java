package lk.ijse.project_dkf.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.bo.custom.OrderRatioBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.tm.OrderRatioTM;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class OrderRatioBOImpl implements OrderRatioBO {
    OrderRatioDAO orderRatioDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    public boolean addRatio(ObservableList<OrderRatioTM> order, String id) throws SQLException {
        return orderRatioDAO.addRatio(order,id);
    }
    public String getNextOrderRatioID() throws SQLException {
        return orderRatioDAO.generateNewID();
    }

}
