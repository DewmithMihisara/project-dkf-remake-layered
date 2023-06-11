package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.CutBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.CutDAO;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.impl.CutDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dto.CutDTO;

import java.sql.SQLException;
import java.util.List;

public class CutBOImpl implements CutBO {
    CutDAO cutDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.CUT);
    OrderRatioDAO orderRatioDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    public List<CutDTO> getAll(String id) throws SQLException {
        return cutDAO.getAll(id);
    }

    public boolean add(CutDTO cutDTO) throws SQLException {
        return cutDAO.add(cutDTO);
    }

    public boolean delete(CutDTO cutDTO) throws SQLException {
        return cutDAO.delete(cutDTO);
    }
    public List<String> loadClothId(String id) throws SQLException {
        return orderRatioDAO.loadClothId(id);
    }
    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
