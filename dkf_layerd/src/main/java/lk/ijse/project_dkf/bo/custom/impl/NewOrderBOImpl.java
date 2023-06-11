package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.NewOrderBO;
import lk.ijse.project_dkf.bo.custom.OrderBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.BuyerDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.entity.Buyer;

import java.util.List;
import java.sql.*;
public class NewOrderBOImpl implements NewOrderBO {
    BuyerDAO buyerDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.BUYER);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);

    public List<String> loadIds() throws SQLException {
        return buyerDAO.loadIds();
    }
    public BuyerDTO searchById(String id) throws SQLException {
        Buyer buyer=buyerDAO.searchById(id);
        return new BuyerDTO(buyer.getBuyerID(), buyer.getBuyerName(), buyer.getBuyerCN(), buyer.getBuyerAddress());
    }
    public String getNextOrderID() throws SQLException {
        return ordersDAO.generateNewID();
    }
}
