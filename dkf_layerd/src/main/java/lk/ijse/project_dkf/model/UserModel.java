package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean addUser(User user) throws SQLException {
        String sql ="INSERT INTO User (UserName, Password, UserEmail, UserContact, UserAddress, Position) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserName(),
                user.getPassword(),
                user.getUserEmail(),
                user.getContact(),
                user.getAddress(),
                user.getPosition()
        );
    }

    public static String getOwnerMail() throws SQLException {
        String sql = "SELECT UserEmail FROM User WHERE Position='owner'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static User getUser(String userName) throws SQLException {
        String sql = "SELECT * FROM User WHERE UserName=?";
        ResultSet resultSet = CrudUtil.execute(sql,userName);
        if (resultSet.next()){
            return new User(
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

    public static boolean update(User user) throws SQLException {
        String sql = "UPDATE User SET UserEmail = ?, UserContact = ?, UserAddress = ? , Password= ? WHERE UserName = ?";
        boolean result = CrudUtil.execute(
                sql,
                user.getUserEmail(),
                user.getContact(),
                user.getAddress(),
                user.getPassword(),
                user.getUserName()
        );
        return result;
    }
}
