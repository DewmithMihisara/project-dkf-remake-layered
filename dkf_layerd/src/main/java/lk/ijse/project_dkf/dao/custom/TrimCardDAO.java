package lk.ijse.project_dkf.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.SuperDAO;
import lk.ijse.project_dkf.view.tm.TrimCardTM;

import java.sql.SQLException;
import java.util.List;

public interface TrimCardDAO extends SuperDAO {
    List<String> loadMaterialId(String id) throws SQLException;

    boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException;

    String generateNewID() throws SQLException;
    String splitOrderId(String currentId);
}
