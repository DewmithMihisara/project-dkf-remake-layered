package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.PackingDAOImpl;
import lk.ijse.project_dkf.dto.PackDTO;
import lk.ijse.project_dkf.dto.StockDTO;

import java.sql.SQLException;
import java.util.List;

public interface PackingBO extends SuperBO {
    List<PackDTO> getAll(String packId) throws SQLException;

    boolean add(PackDTO packDTO) throws SQLException;

    boolean delete(PackDTO packDTO) throws SQLException;
    boolean stockAdd(PackDTO packDTO, StockDTO stockDTO) throws SQLException;
    List<String> loadClothId(String id) throws SQLException;
    List<String> loadOrderIds() throws SQLException;
    boolean add(StockDTO stockDTO) throws SQLException;
}
