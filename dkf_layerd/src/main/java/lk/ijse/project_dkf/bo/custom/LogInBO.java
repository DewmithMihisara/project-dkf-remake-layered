package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;

public interface LogInBO extends SuperBO {
    UserDTO isCorrect(String usrName) throws SQLException ;
    void save(LogHistoryDTO logHistoryDTO) throws SQLException;
}
