package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.PasswordSettingsBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.entity.User;

import java.sql.SQLException;

public class PasswordSettingsBOImpl implements PasswordSettingsBO {
    UserDAO userDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);
    public boolean update(UserDTO userDTO) throws SQLException {
        return userDAO.update(new User(userDTO.getUserName(),userDTO.getPassword(),userDTO.getUserEmail(),userDTO.getContact(),userDTO.getAddress(),userDTO.getPosition()));
    }
}
