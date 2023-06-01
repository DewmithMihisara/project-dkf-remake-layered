package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Material;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.dto.tm.MaterialTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class MaterialModel {
    public static boolean add(Material material) throws SQLException {
        String sql ="INSERT INTO Material (OrderID, MatID, Time, MaterialQty ,Date ) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                material.getOrderID(),
                material.getMid(),
                material.getTime(),
                material.getQty(),
                material.getDate()
        );
    }
    public static boolean delete(MaterialTM materialTM, String selectedItem) throws SQLException {
        String sql="DELETE FROM Material WHERE OrderId=? AND MatID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                selectedItem,
                materialTM.getOid(),
                materialTM.getDate(),
                materialTM.getTime()
        );
        return result;
    }
    public static List<Material> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Material WHERE OrderID =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Material> materials=new ArrayList<>();
        while (resultSet.next()){
            String oId= resultSet.getString(1);
            String mId=resultSet.getString(2);
            Time time=resultSet.getTime(3);
            int qty=resultSet.getInt(4);
            Date date=resultSet.getDate(5);

            Material material=new Material(oId,mId,time,qty,date);
            materials.add(material);
        }
        return materials;
    }
}
