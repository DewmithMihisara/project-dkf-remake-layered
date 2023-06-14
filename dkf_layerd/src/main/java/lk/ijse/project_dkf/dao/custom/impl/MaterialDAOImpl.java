package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.MaterialDAO;
import lk.ijse.project_dkf.entity.Material;
import lk.ijse.project_dkf.view.tm.MaterialTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    public boolean add(Material material) throws SQLException {
        String sql ="INSERT INTO Material (OrderID, MatID, Time, MaterialQty ,Date ) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                material.getOrderID(),
                material.getMatID(),
                material.getTime(),
                material.getMaterialQty(),
                material.getDate()
        );
    }

    @Override
    public List<Material> getAll() throws SQLException {
        return null;
    }

    public boolean delete(Material material) throws SQLException {
        String sql="DELETE FROM Material WHERE OrderId=? AND MatID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                material.getOrderID(),
                material.getMatID(),
                material.getDate(),
                material.getTime()
        );
        return result;
    }

    public List<Material> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Material WHERE OrderID =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Material> materials =new ArrayList<>();
        while (resultSet.next()){
            String oId= resultSet.getString(1);
            String mId=resultSet.getString(2);
            Time time=resultSet.getTime(3);
            int qty=resultSet.getInt(4);
            Date date=resultSet.getDate(5);

            Material material =new Material(oId,mId,time,qty,date);
            materials.add(material);
        }
        return materials;
    }
}
