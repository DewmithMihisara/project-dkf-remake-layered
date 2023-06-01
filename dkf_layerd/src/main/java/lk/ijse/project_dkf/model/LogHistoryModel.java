package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.LogHistory;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogHistoryModel {
    public static void save(LogHistory logHistory) throws SQLException {
        String sql ="INSERT INTO LogHistory (UserName, LogIn, logOut) VALUES(?, ?, ?)";
        CrudUtil.execute(
                sql,
                logHistory.getUsrName(),
                logHistory.getLogIn(),
                logHistory.getLogOut()
        );
    }

    public static List<LogHistory> getAll() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM LogHistory";
        ResultSet resultSet = CrudUtil.execute(sql);

        ArrayList<LogHistory> logHistories=new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            LocalDateTime login = LocalDateTime.parse(resultSet.getString(2), formatter);
            LocalDateTime logOut = LocalDateTime.parse(resultSet.getString(3), formatter);

            LogHistory logHistory=new LogHistory(name,login,logOut);
            logHistories.add(logHistory);
        }
        return logHistories;
    }
    public static boolean isHave() throws SQLException {
        String sql = "SELECT * FROM LogHistory";
        ResultSet resultSet = CrudUtil.execute(sql);
        return resultSet.next();
    }
}
