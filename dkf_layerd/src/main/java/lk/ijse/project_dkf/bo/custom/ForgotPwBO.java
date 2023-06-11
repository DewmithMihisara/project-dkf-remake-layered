package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public interface ForgotPwBO extends SuperBO {
    boolean updatePw(String text, String usrName) throws SQLException ;
    String getOwnerMail() throws SQLException;
}
