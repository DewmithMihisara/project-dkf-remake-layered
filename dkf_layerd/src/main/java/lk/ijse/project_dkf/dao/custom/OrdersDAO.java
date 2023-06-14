package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Order, String> {
    List<String> loadOrderIds() throws SQLException;


    String generateNewID() throws SQLException;
    String splitOrderId(String currentId);

    BuyerDTO searchBuyer(String id) throws SQLException;
}
