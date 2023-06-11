package lk.ijse.project_dkf.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.tm.OrderRatioTM;

import java.sql.SQLException;

public interface OrderRatioBO extends SuperBO {
    boolean addRatio(ObservableList<OrderRatioTM> order, String id) throws SQLException ;
    String getNextOrderRatioID() throws SQLException ;
}
