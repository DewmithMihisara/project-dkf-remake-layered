package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface LogHistoryDAO extends CrudDAO<LogHistoryDTO, String> {
    void save(LogHistoryDTO logHistoryDTO) throws SQLException;

    boolean isHave() throws SQLException;
}
