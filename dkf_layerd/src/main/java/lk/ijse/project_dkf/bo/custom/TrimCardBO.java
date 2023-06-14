package lk.ijse.project_dkf.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.view.tm.TrimCardTM;

import java.sql.SQLException;

public interface TrimCardBO extends SuperBO {
    boolean placeOrder() throws SQLException;
    boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException;
    String getNextTrimID() throws SQLException;
}
