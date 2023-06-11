package lk.ijse.project_dkf.dao.custom;

import lk.ijse.project_dkf.dao.CrudDAO;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {
    boolean updatePw(String text, String usrName) throws SQLException;

    UserDTO isCorrect(String usrName) throws SQLException;

    boolean isDuplicate(User user) throws SQLException;

    String getOwnerMail() throws SQLException;

    UserDTO getUser(String userName) throws SQLException;

    boolean update(User user) throws SQLException;
}
