package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.LogHistoryDAO;
import lk.ijse.project_dkf.dto.LogHistoryDTO;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogHistoryDAOImpl implements LogHistoryDAO {
    public void save(LogHistoryDTO logHistoryDTO) throws SQLException {
        String sql ="INSERT INTO LogHistoryDTO (UserName, LogIn, logOut) VALUES(?, ?, ?)";
        CrudUtil.execute(
                sql,
                logHistoryDTO.getUsrName(),
                logHistoryDTO.getLogIn(),
                logHistoryDTO.getLogOut()
        );
    }

    @Override
    public boolean add(LogHistoryDTO dto) throws SQLException {
        return false;
    }

    public List<LogHistoryDTO> getAll() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM LogHistoryDTO";
        ResultSet resultSet = CrudUtil.execute(sql);

        ArrayList<LogHistoryDTO> logHistories=new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            LocalDateTime login = LocalDateTime.parse(resultSet.getString(2), formatter);
            LocalDateTime logOut = LocalDateTime.parse(resultSet.getString(3), formatter);

            LogHistoryDTO logHistoryDTO =new LogHistoryDTO(name,login,logOut);
            logHistories.add(logHistoryDTO);
        }
        return logHistories;
    }

    @Override
    public List<LogHistoryDTO> getAll(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(LogHistoryDTO dto) throws SQLException {
        return false;
    }


    public boolean isHave() throws SQLException {
        String sql = "SELECT * FROM LogHistoryDTO";
        ResultSet resultSet = CrudUtil.execute(sql);
        return resultSet.next();
    }
}
