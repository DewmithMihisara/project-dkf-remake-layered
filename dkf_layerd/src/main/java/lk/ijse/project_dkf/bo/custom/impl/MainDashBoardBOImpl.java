package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.MainDashBoardBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.LogHistoryDAO;
import lk.ijse.project_dkf.dao.custom.impl.LogHistoryDAOImpl;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.entity.LogHistory;

import java.sql.SQLException;

public class MainDashBoardBOImpl implements MainDashBoardBO {
    LogHistoryDAO logHistoryDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.LOG_HISTORY);

    public void saveLogHistory(LogHistoryDTO logHistoryDTO) throws SQLException {
        logHistoryDAO.save(new LogHistory(logHistoryDTO.getUsrName(),logHistoryDTO.getLogIn(),logHistoryDTO.getLogOut()));
    }
}
