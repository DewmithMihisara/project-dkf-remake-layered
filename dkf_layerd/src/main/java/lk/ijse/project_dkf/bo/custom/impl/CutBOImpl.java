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
import lk.ijse.project_dkf.entity.Cut;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CutBOImpl implements CutBO {
    CutDAO cutDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.CUT);
    OrderRatioDAO orderRatioDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    public List<CutDTO> getAll(String id) throws SQLException {
        List <Cut> cuts= cutDAO.getAll(id);
        List <CutDTO>cutDTOS=new ArrayList<>();
        for (Cut c: cuts){
            cutDTOS.add(new CutDTO(c.getOrderID(),c.getClotheID(),c.getDate(),c.getTime(),Integer.parseInt(c.getCutQty()),c.getType(),c.getSize()));
        }
        return cutDTOS;
    }

    public boolean add(CutDTO cutDTO) throws SQLException {
        return cutDAO.add(new Cut(cutDTO.getCutID(),cutDTO.getClothId(),cutDTO.getDate(),cutDTO.getTime(),String.valueOf(cutDTO.getCutQty()),cutDTO.getType(),cutDTO.getSize()));
    }

    public boolean delete(CutDTO cutDTO) throws SQLException {
        return cutDAO.delete(new Cut(cutDTO.getOrderId(),cutDTO.getClothId(),cutDTO.getDate(),cutDTO.getTime(),String.valueOf(cutDTO.getCutQty()),cutDTO.getType(),cutDTO.getSize()));
    }
    public List<String> loadClothId(String id) throws SQLException {
        return orderRatioDAO.loadClothId(id);
    }
    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
