package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.OrderBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.PackingDAO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.PackingDAOImpl;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.dto.OrderRatioDTO;
import lk.ijse.project_dkf.dto.PackDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    OrderRatioDAO orderRatioDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    PackingDAO packingDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.PACKING);

    public boolean addOrder(OrderDTO orderDTO) throws SQLException {
        return ordersDAO.add(orderDTO);
    }

    public String getNextOrderID() throws SQLException {
        return ordersDAO.generateNewID();
    }

    public boolean delete(OrderDTO orderDTO) throws SQLException {
        return ordersDAO.delete(orderDTO);
    }

    public List<String> loadClothId(String id) throws SQLException {
        return orderRatioDAO.loadClothId(id);
    }

    public OrderRatioDTO ratio(String oId, String clID) throws SQLException {
        return orderRatioDAO.loadSize(oId, clID);
    }

    public List<PackDTO> getAll(String packId) throws SQLException {
        return packingDAO.getAll(packId);
    }

    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
