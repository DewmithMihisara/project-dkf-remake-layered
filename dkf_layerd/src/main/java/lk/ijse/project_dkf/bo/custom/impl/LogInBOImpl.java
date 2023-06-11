package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.LogInBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.LogHistoryDAO;
import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dao.custom.impl.LogHistoryDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.entity.LogHistory;

import java.sql.SQLException;

public class LogInBOImpl implements LogInBO {
    UserDAO userDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);
    LogHistoryDAO logHistoryDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.LOG_HISTORY);
    public UserDTO isCorrect(String usrName) throws SQLException {
        return userDAO.isCorrect(usrName);
    }
    public void save(LogHistoryDTO logHistoryDTO) throws SQLException {
        logHistoryDAO.save(new LogHistory(logHistoryDTO.getUsrName(),logHistoryDTO.getLogIn(),logHistoryDTO.getLogOut()));
    }

}
