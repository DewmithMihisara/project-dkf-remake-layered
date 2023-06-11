package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.OutputDAO;
import lk.ijse.project_dkf.dto.OutputDTO;
import lk.ijse.project_dkf.tm.OutputTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutputDAOImpl implements OutputDAO {
    public boolean add(OutputDTO outputDTO) throws SQLException {
        String sql ="INSERT INTO OutputDTO (OutputID, Day, Time, ClotheID, size, DailyOut ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                outputDTO.getOId(),
                outputDTO.getDate(),
                outputDTO.getTime(),
                outputDTO.getClId(),
                outputDTO.getSize(),
                outputDTO.getOut()
        );
    }

    @Override
    public List<OutputDTO> getAll() throws SQLException {
        return null;
    }

    public List<OutputDTO> getAll(String ids) throws SQLException {
        String sql = "SELECT * FROM OutputDTO WHERE OutputID =?";
        ResultSet resultSet = CrudUtil.execute(sql,ids);

        ArrayList<OutputDTO> outputDTOS =new ArrayList<>();
        while (resultSet.next()){
            String id= resultSet.getString(1);
            Date date=resultSet.getDate(2);
            Time time=resultSet.getTime(3);
            String clId=resultSet.getString(4);
            String size=resultSet.getString(5);
            int out=resultSet.getInt(6);

            OutputDTO outputDTO =new OutputDTO(id,date,time,clId,size,out);
            outputDTOS.add(outputDTO);
        }
        return outputDTOS;
    }
    public boolean delete(OutputDTO outputDTO) throws SQLException {
        String sql="DELETE FROM OutputDTO WHERE OutputID=? AND Day=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                outputDTO.getOId(),
                outputDTO.getDate(),
                outputDTO.getTime()
        );
        return result;
    }
}
