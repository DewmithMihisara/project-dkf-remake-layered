package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.SettingsBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.LogHistoryDAO;
import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class SettingsBOImpl implements SettingsBO {
    LogHistoryDAO logHistoryDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.LOG_HISTORY);
    UserDAO userDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);
    public boolean isHave() throws SQLException {
        return logHistoryDAO.isHave();
    }
    public boolean update(UserDTO userDTO) throws SQLException {
        return userDAO.update(userDTO);
    }
    public List<LogHistoryDTO> getAll() throws SQLException {
        return logHistoryDAO.getAll();
    }
}
