package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.UserDTO;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserDTO, String> {
    boolean updatePw(String text, String usrName) throws SQLException;

    UserDTO isCorrect(String usrName) throws SQLException;

    boolean isDuplicate(UserDTO userDTO) throws SQLException;

    String getOwnerMail() throws SQLException;

    UserDTO getUser(String userName) throws SQLException;

    boolean update(UserDTO userDTO) throws SQLException;
}
