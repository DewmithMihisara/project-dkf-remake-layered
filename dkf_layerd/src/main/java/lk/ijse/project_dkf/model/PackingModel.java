package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackingModel {
    public static List<Pack> getAll(String packId) throws SQLException {
        String sql = "SELECT * FROM Packing WHERE PackID =?";
        ResultSet resultSet = CrudUtil.execute(sql,packId);

        ArrayList<Pack> packs=new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            Date date=resultSet.getDate(2);
            Time time=resultSet.getTime(3);
            String clr=resultSet.getString(4);
            String size=resultSet.getString(5);
            int qty=resultSet.getInt(6);

            Pack pack=new Pack(id, date,time, clr,size, qty);
            packs.add(pack);
        }
        return packs;
    }
    public static boolean add(Pack pack) throws SQLException {
        String sql ="INSERT INTO Packing (PackID, Date, Time, ClotheID, Size, PackQty ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                pack.getPackID(),
                pack.getDate(),
                pack.getTime(),
                pack.getClId(),
                pack.getSize(),
                pack.getPackQty()
        );
    }
    public static boolean delete(PackingTM packingTM, String id) throws SQLException {
        String sql="DELETE FROM Packing WHERE PackID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                id,
                packingTM.getDate(),
                packingTM.getTime()
        );
        return result;
    }
}
