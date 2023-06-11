package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;

public interface PasswordSettingsBO extends SuperBO {
    boolean update(UserDTO userDTO) throws SQLException;
}
