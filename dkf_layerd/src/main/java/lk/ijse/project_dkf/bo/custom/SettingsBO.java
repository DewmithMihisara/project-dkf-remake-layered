package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface SettingsBO extends SuperBO {
    boolean isHave() throws SQLException;

    boolean update(UserDTO userDTO) throws SQLException;

    List<LogHistoryDTO> getAll() throws SQLException;
}
