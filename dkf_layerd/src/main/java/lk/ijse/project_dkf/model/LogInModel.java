package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInModel {
    public static User isCorrect(String usrName) throws SQLException {

        String sql = "SELECT * FROM User WHERE UserName = ?";
        ResultSet resultSet = CrudUtil.execute(sql, usrName);

        if (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );

        }
        return new User();
    }

    public static boolean isCorrextusr(String text) throws SQLException {
        String sql = "SELECT * FROM User WHERE USERNAME=?";
        ResultSet resultSet = CrudUtil.execute(sql,text);

        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}
