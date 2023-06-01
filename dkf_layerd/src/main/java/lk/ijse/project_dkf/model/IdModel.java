package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdModel {
    public static List<String> loadOrderIds() throws SQLException {
        String sql="SELECT OrderID FROM Orders";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public static List<String> loadClothId(String id) throws SQLException {
        String sql="SELECT ClotheID FROM OrderRatio WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public static List<String> loadMaterialId(String id) throws SQLException {
        String sql="SELECT TrimID FROM TrimCard WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
