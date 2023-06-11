package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<OrderDTO, String> {
    List<String> loadOrderIds() throws SQLException;


    String generateNewID() throws SQLException;

    BuyerDTO searchBuyer(String id) throws SQLException;
}
