package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.GmailConfirmBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.entity.User;

import java.sql.SQLException;

public class GmailConfirmBOImpl implements GmailConfirmBO {
    UserDAO userDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);
    public boolean addUser(UserDTO userDTO) throws SQLException {
        return userDAO.add(new User(userDTO.getUserName(),userDTO.getPassword(),userDTO.getUserEmail(),userDTO.getContact(),userDTO.getAddress(),userDTO.getPosition()));
    }
}
