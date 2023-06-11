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
import lk.ijse.project_dkf.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialBOImpl implements MaterialBO {
    MaterialDAO materialDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.MATERIAL);
    TrimCardDAO trimCardDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.TRIM_CARD);
    OrdersDAO ordersDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);

    public boolean add(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.add(new Material(materialDTO.getOrderID(),materialDTO.getMid(),materialDTO.getTime(),materialDTO.getQty(),materialDTO.getDate()));
    }
    public boolean delete(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.delete(new Material(materialDTO.getOrderID(),materialDTO.getMid(),materialDTO.getTime(),materialDTO.getQty(),materialDTO.getDate()));
    }
    public List<MaterialDTO> getAll(String id) throws SQLException {
        List<Material>materials=materialDAO.getAll(id);
        List<MaterialDTO>materialDTOS=new ArrayList<>();
        for (Material m: materials){
            materialDTOS.add(new MaterialDTO(m.getOrderID(),m.getMatID(),m.getTime(),m.getMaterialQty(),m.getDate()));
        }
        return materialDTOS;
    }
    public List<String> loadMaterialId(String id) throws SQLException {
        return trimCardDAO.loadMaterialId(id);
    }
    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
