package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;

public interface GmailConfirmBO extends SuperBO {
    boolean addUser(UserDTO userDTO) throws SQLException ;
}
