package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.ForgotPwBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.UserDAO;
import lk.ijse.project_dkf.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public class ForgotPwBOImpl implements ForgotPwBO {
    UserDAO userDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);

    public boolean updatePw(String text, String usrName) throws SQLException {
        return userDAO.updatePw(text,usrName);
    }
    public String getOwnerMail() throws SQLException {
        return userDAO.getOwnerMail();
    }
}
