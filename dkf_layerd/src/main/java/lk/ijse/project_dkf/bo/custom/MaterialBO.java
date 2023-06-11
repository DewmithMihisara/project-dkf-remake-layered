package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.project_dkf.dto.MaterialDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaterialBO extends SuperBO {
    boolean add(MaterialDTO materialDTO) throws SQLException ;
    boolean delete(MaterialDTO materialDTO) throws SQLException ;
    List<MaterialDTO> getAll(String id) throws SQLException ;
    List<String> loadMaterialId(String id) throws SQLException;
    List<String> loadOrderIds() throws SQLException;
}
