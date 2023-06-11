package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.dto.OrderRatioDTO;
import lk.ijse.project_dkf.dto.PackDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean addOrder(OrderDTO orderDTO) throws SQLException;

    String getNextOrderID() throws SQLException;

    boolean delete(OrderDTO orderDTO) throws SQLException;

    List<String> loadClothId(String id) throws SQLException;

    OrderRatioDTO ratio(String oId, String clID) throws SQLException;

    List<PackDTO> getAll(String packId) throws SQLException;

    List<String> loadOrderIds() throws SQLException;
}