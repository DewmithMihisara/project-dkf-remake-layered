package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.CutDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dto.CutDTO;

import java.sql.SQLException;
import java.util.List;

public interface CutBO extends SuperBO {
    List<CutDTO> getAll(String id) throws SQLException;

    boolean add(CutDTO cutDTO) throws SQLException;

    boolean delete(CutDTO cutDTO) throws SQLException;

    List<String> loadClothId(String id) throws SQLException;
    List<String> loadOrderIds() throws SQLException ;
}
