package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.PackingDAO;
import lk.ijse.project_dkf.entity.Packing;
import lk.ijse.project_dkf.view.tm.PackingTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackingDAOImpl implements PackingDAO {
    public List<Packing> getAll(String packId) throws SQLException {
        String sql = "SELECT * FROM Packing WHERE PackID =?";
        ResultSet resultSet = CrudUtil.execute(sql,packId);

        ArrayList<Packing> packing =new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            Date date=resultSet.getDate(2);
            Time time=resultSet.getTime(3);
            String clId=resultSet.getString(4);
            String size=resultSet.getString(5);
            int qty=resultSet.getInt(6);

            Packing packing1 =new Packing(id, date,time, clId,size, qty);
            packing.add(packing1);
        }
        return packing;
    }
    public boolean add(Packing packing) throws SQLException {
        String sql ="INSERT INTO Packing (PackID, Date, Time, ClotheID, Size, PackQty ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                packing.getPackID(),
                packing.getDate(),
                packing.getTime(),
                packing.getClotheID(),
                packing.getSize(),
                packing.getPackQty()
        );
    }

    @Override
    public List<Packing> getAll() throws SQLException {
        return null;
    }

    public boolean delete(Packing packing) throws SQLException {
        String sql="DELETE FROM Packing WHERE PackID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                packing.getPackID(),
                packing.getDate(),
                packing.getTime()
        );
        return result;
    }
}
