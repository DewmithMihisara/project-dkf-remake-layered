package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.LogHistoryDAO;
import lk.ijse.project_dkf.entity.LogHistory;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogHistoryDAOImpl implements LogHistoryDAO {
    public void save(LogHistory logHistory) throws SQLException {
        String sql ="INSERT INTO LogHistory (UserName, LogIn, logOut) VALUES(?, ?, ?)";
        CrudUtil.execute(
                sql,
                logHistory.getUserName(),
                logHistory.getLogIn(),
                logHistory.getLogOut()
        );
    }

    @Override
    public boolean add(LogHistory logHistory) throws SQLException {
        return false;
    }

    public List<LogHistory> getAll() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM LogHistory";
        ResultSet resultSet = CrudUtil.execute(sql);

        ArrayList<LogHistory> logHistories=new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            LocalDateTime login = LocalDateTime.parse(resultSet.getString(2), formatter);
            LocalDateTime logOut = LocalDateTime.parse(resultSet.getString(3), formatter);

            LogHistory logHistory =new LogHistory(name,login,logOut);
            logHistories.add(logHistory);
        }
        return logHistories;
    }

    @Override
    public List<LogHistory> getAll(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(LogHistory logHistory) throws SQLException {
        return false;
    }


    public boolean isHave() throws SQLException {
        String sql = "SELECT * FROM LogHistory";
        ResultSet resultSet = CrudUtil.execute(sql);
        return resultSet.next();
    }
}
