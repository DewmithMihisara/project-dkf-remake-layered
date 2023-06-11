package lk.ijse.project_dkf.dao;

import lk.ijse.project_dkf.dto.BuyerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO{
    boolean add(T dto) throws SQLException;
    List<T> getAll() throws SQLException;
    List<T> getAll(ID id) throws SQLException;
    boolean delete(T dto) throws SQLException;

}
