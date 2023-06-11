package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.OutputDAOImpl;
import lk.ijse.project_dkf.dto.OutputDTO;

import java.sql.SQLException;
import java.util.List;

public interface OutputBO extends SuperBO {
    boolean add(OutputDTO outputDTO) throws SQLException;

    List<OutputDTO> getAll(String ids) throws SQLException;

    boolean delete(OutputDTO outputDTO) throws SQLException;

    List<String> loadClothId(String id) throws SQLException;

    List<String> loadOrderIds() throws SQLException;
}
