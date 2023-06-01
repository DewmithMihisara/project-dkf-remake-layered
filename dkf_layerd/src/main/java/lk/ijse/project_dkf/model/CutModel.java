package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutModel {
    public static List<Cut> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Cut WHERE OrderId =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Cut> cuts=new ArrayList<>();
        while (resultSet.next()){
            String cutID = resultSet.getString(1);
            String clId=resultSet.getString(2);
            Date date=resultSet.getDate(3);
            Time time=resultSet.getTime(4);
            int qty=resultSet.getInt(5);
            String type= resultSet.getString(6);
            String size=resultSet.getString(7);

            Cut cut=new Cut(cutID, clId, date, time, qty, type, size);
            cuts.add(cut);
        }
        return cuts;
    }

    public static boolean add(Cut cut) throws SQLException {
        String sql ="INSERT INTO Cut (OrderId, ClotheID, Date, Time, CutQty, Type, Size ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                cut.getCutID(),
                cut.getClothId(),
                cut.getDate(),
                cut.getTime(),
                cut.getCutQty(),
                cut.getType(),
                cut.getSize()
        );
    }

    public static boolean delete(CutTM cutTM, String selectedItem) throws SQLException {
        String sql="DELETE FROM Cut WHERE OrderId=? AND ClotheId=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                selectedItem,
                cutTM.getClothID(),
                cutTM.getDate(),
                cutTM.getTime()
        );
        return result;
    }
}
