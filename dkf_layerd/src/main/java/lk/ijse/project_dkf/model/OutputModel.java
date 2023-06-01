package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.OutputTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutputModel {
    public static boolean add(Output output) throws SQLException{
        String sql ="INSERT INTO Output (OutputID, Day, Time, ClotheID, size, DailyOut ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                output.getOId(),
                output.getDate(),
                output.getTime(),
                output.getClId(),
                output.getSize(),
                output.getOut()
        );
    }
    public static List<Output> getAll(String ids) throws SQLException {
        String sql = "SELECT * FROM Output WHERE OutputID =?";
        ResultSet resultSet = CrudUtil.execute(sql,ids);

        ArrayList<Output> outputs=new ArrayList<>();
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
    public static boolean delete(OutputTM output, String id) throws SQLException {
        String sql="DELETE FROM Output WHERE OutputID=? AND Day=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                id,
                output.getDate(),
                output.getTime()
        );
        return result;
    }
}
