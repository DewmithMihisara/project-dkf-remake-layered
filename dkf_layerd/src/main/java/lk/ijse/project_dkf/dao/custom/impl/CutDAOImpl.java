package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.CutDAO;
import lk.ijse.project_dkf.entity.Cut;
import lk.ijse.project_dkf.view.tm.CutTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CutDAOImpl implements CutDAO {
    public List<Cut> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Cut WHERE OrderId =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Cut> cuts =new ArrayList<>();
        while (resultSet.next()){
            cuts.add(new Cut(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getTime(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            ));
        }
        return cuts;
    }

    @Override
    public List<Cut> getAll() throws SQLException {
        return null;
    }

    public boolean add(Cut cut) throws SQLException {
        String sql ="INSERT INTO Cut (OrderId, ClotheID, Date, Time, CutQty, Type, Size ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                cut.getOrderID(),
                cut.getClotheID(),
                cut.getDate(),
                cut.getTime(),
                cut.getCutQty(),
                cut.getType(),
                cut.getSize()
        );
    }

    public boolean delete(Cut cut) throws SQLException {
        String sql="DELETE FROM Cut WHERE OrderId=? AND ClotheId=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                cut.getOrderID(),
                cut.getClotheID(),
                cut.getDate(),
                cut.getTime()

        );
        return result;
    }

}
