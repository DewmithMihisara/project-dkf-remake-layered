package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dto.UserDTO;
import lk.ijse.project_dkf.entity.User;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public boolean updatePw(String text, String usrName) throws SQLException {
        String sql = "UPDATE User SET Password=? WHERE UserName = ?";
        boolean result = CrudUtil.execute(sql, text,usrName);
        return result;
    }
    public UserDTO isCorrect(String usrName) throws SQLException {
        String sql = "SELECT * FROM User WHERE UserName = ?";
        ResultSet resultSet = CrudUtil.execute(sql, usrName);

        if (resultSet.next()) {
            return new UserDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );

        }
        return new UserDTO();
    }
    public  boolean isDuplicate(User user) throws SQLException {
        String sql="INSERT INTO User(UserName,Password ,UserEmail,UserContact, UserAddress,Position) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserName(),
                user.getPassword(),
                user.getUserEmail(),
                user.getUserContact(),
                user.getUserAddress(),
                user.getPosition()
        );
    }
    public boolean add(User user) throws SQLException {
        String sql ="INSERT INTO User (UserName, Password, UserEmail, UserContact, UserAddress, Position) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserName(),
                user.getPassword(),
                user.getUserEmail(),
                user.getUserContact(),
                user.getUserAddress(),
                user.getPosition()
        );
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        return false;
    }

    public String getOwnerMail() throws SQLException {
        String sql = "SELECT UserEmail FROM User WHERE Position='owner'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public UserDTO getUser(String userName) throws SQLException {
        String sql = "SELECT * FROM User WHERE UserName=?";
        ResultSet resultSet = CrudUtil.execute(sql,userName);
        if (resultSet.next()){
            return new UserDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    public  boolean update(User user) throws SQLException {
        String sql = "UPDATE User SET UserEmail = ?, UserContact = ?, UserAddress = ? , Password= ? WHERE UserName = ?";
        boolean result = CrudUtil.execute(
                sql,
                user.getUserEmail(),
                user.getUserContact(),
                user.getUserAddress(),
                user.getPassword(),
                user.getUserName()
        );
        return result;
    }
}
