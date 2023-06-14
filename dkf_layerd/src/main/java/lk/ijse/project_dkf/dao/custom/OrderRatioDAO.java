package lk.ijse.project_dkf.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.SuperDAO;
import lk.ijse.project_dkf.dto.OrderRatioDTO;
import lk.ijse.project_dkf.view.tm.OrderRatioTM;

import java.sql.SQLException;
import java.util.List;

public interface OrderRatioDAO extends SuperDAO {
    List<String> loadClothId(String id) throws SQLException;

    OrderRatioDTO loadSize(String oId, String clID) throws SQLException;

    boolean addRatio(ObservableList<OrderRatioTM> order, String id) throws SQLException;

    String generateNewID() throws SQLException;
    String splitOrderId(String currentId);

    String searchClothDetail(String selectedItem) throws SQLException;
}
