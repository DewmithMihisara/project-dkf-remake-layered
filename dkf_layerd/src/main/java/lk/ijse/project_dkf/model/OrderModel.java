package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.Order;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean addOrder(Order order) throws SQLException {
        String sql ="INSERT INTO Orders (OrderID,BuyerID,Dedline,TtlQty,DailyOutQty,PayTerm,OrderDate ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                order.getOrderId(),
                order.getCompId(),
                order.getDline(),
                order.getTtlQty(),
                order.getDailyOut(),
                order.getPayment(),
                order.getOrderDate()
        );
    }
    public static String getNextOrderID() throws SQLException {
        String sql="SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("o");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "o" + id;
        }
        return "o10000";
    }
    public static boolean delete(String id) throws SQLException {
        String sql="DELETE FROM Orders WHERE OrderID=? ";
        boolean result = CrudUtil.execute(
                sql,
                id
        );
        return result;
    }
}
