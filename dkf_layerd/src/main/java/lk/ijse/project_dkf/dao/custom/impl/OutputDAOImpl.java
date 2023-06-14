package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.OutputDAO;
import lk.ijse.project_dkf.entity.Output;
import lk.ijse.project_dkf.view.tm.OutputTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OutputDAOImpl implements OutputDAO {
    public boolean add(Output output) throws SQLException {
        String sql ="INSERT INTO Output (OutputID, Day, Time, ClotheID, size, DailyOut ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                output.getOutputID(),
                output.getDate(),
                output.getTime(),
                output.getClothID(),
                output.getSize(),
                output.getDailyOut()
        );
    }

    @Override
    public List<Output> getAll() throws SQLException {
        return null;
    }

    public List<Output> getAll(String ids) throws SQLException {
        String sql = "SELECT * FROM Output WHERE OutputID =?";
        ResultSet resultSet = CrudUtil.execute(sql,ids);

        ArrayList<Output> outputs =new ArrayList<>();
        while (resultSet.next()){
            String id= resultSet.getString(1);
            Date date=resultSet.getDate(2);
            Time time=resultSet.getTime(3);
            String clId=resultSet.getString(4);
            String size=resultSet.getString(5);
            int out=resultSet.getInt(6);

            Output output=new Output(id,date,time,clId,size,out);
            outputs.add(output);
        }
        return outputs;
    }
    public boolean delete(Output output) throws SQLException {
        String sql="DELETE FROM Output WHERE OutputID=? AND Day=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                output.getOutputID(),
                output.getDate(),
                output.getTime()
        );
        return result;
    }
}
