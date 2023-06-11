package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.MaterialBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.MaterialDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.TrimCardDAO;
import lk.ijse.project_dkf.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.TrimCardDAOImpl;
import lk.ijse.project_dkf.dto.MaterialDTO;

import java.sql.SQLException;
import java.util.List;

public class MaterialBOImpl implements MaterialBO {
    MaterialDAO materialDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.MATERIAL);
    TrimCardDAO trimCardDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.TRIM_CARD);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);

    public boolean add(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.add(materialDTO);
    }
    public boolean delete(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.delete(materialDTO);
    }
    public List<MaterialDTO> getAll(String id) throws SQLException {
        return materialDAO.getAll(id);
    }
    public List<String> loadMaterialId(String id) throws SQLException {
        return trimCardDAO.loadMaterialId(id);
    }
    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
