package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.project_dkf.dto.BuyerDTO;

import java.sql.SQLException;
import java.util.List;

public interface NewOrderBO extends SuperBO {
    List<String> loadIds() throws SQLException ;
    BuyerDTO searchById(String id) throws SQLException;
    String getNextOrderID() throws SQLException;
}
