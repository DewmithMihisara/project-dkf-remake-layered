package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;

public class ForgotPwModel {
    public static boolean updatePw(String text, String usrName) throws SQLException {
        String sql = "UPDATE User SET Password=? WHERE UserName = ?";
        boolean result = CrudUtil.execute(sql, text,usrName);
        return result;
    }
}
