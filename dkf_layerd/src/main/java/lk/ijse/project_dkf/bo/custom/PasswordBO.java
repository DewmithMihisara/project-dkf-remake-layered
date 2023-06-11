package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;

import java.sql.SQLException;

public interface PasswordBO extends SuperBO {
    String getOwnerMail() throws SQLException;
}
