package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.entity.Buyer;
import lk.ijse.project_dkf.tm.BuyerTM;

import java.sql.SQLException;
import java.util.List;

public interface BuyerDAO extends CrudDAO<Buyer, String> {
    BuyerDTO search(String id) throws SQLException;

    boolean update(Buyer entity) throws SQLException;

    String generateNewID() throws SQLException;

    List<String> loadIds() throws SQLException;

    Buyer searchById(String id) throws SQLException;
}
