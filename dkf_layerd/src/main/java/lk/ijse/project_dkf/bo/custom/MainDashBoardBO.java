package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;

import java.sql.SQLException;

public interface MainDashBoardBO extends SuperBO {
    void saveLogHistory(LogHistoryDTO logHistoryDTO) throws SQLException;
}
