package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.PackingBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.PackingDAO;
import lk.ijse.project_dkf.dao.custom.StockDAO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.PackingDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.StockDAOImpl;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.PackDTO;
import lk.ijse.project_dkf.dto.StockDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PackingBOImpl implements PackingBO {
    PackingDAO packingDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.PACKING);
    OrderRatioDAO orderRatioDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    StockDAO stockDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.STOCK);
    public List<PackDTO> getAll(String packId) throws SQLException {
        return packingDAO.getAll(packId);
    }
    public boolean add(PackDTO packDTO) throws SQLException {
        return packingDAO.add(packDTO);
    }
    public boolean add(StockDTO stockDTO) throws SQLException {
        return stockDAO.add(stockDTO);
    }
    public boolean delete(PackDTO packDTO) throws SQLException {
        return packingDAO.delete(packDTO);
    }
    public List<String> loadClothId(String id) throws SQLException {

        return orderRatioDAO.loadClothId(id);
    }
    public List<String> loadOrderIds() throws SQLException {

        return ordersDAO.loadOrderIds();
    }
    public boolean stockAdd(PackDTO packDTO, StockDTO stockDTO) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isPackAdd = add(packDTO);
            if (isPackAdd){
                boolean isStockAdd= add(stockDTO);
                if (isStockAdd){
                    System.out.println(stockDTO);
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
