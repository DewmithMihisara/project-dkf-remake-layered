package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.entity.LogHistory;

import java.sql.SQLException;
import java.util.List;

public interface LogHistoryDAO extends CrudDAO<LogHistory, String> {
    void save(LogHistory logHistory) throws SQLException;

    boolean isHave() throws SQLException;
}
