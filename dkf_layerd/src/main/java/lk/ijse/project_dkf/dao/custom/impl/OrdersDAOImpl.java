package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.entity.Order;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    public List<String> loadOrderIds() throws SQLException {
        String sql="SELECT OrderID FROM Orders";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public boolean add(Order order) throws SQLException {
        String sql ="INSERT INTO Orders (OrderID,BuyerID,Dedline,TtlQty,DailyOutQty,PayTerm,OrderDate ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                order.getOrderID(),
                order.getBuyerID(),
                order.getDeadline(),
                order.getTtlQty(),
                order.getDailyOutQty(),
                order.getPayTerm(),
                order.getOrderDate()
        );
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Order> getAll(String s) throws SQLException {
        return null;
    }

    public boolean delete(Order order) throws SQLException {
        String sql="DELETE FROM Orders WHERE OrderID=? ";
        boolean result = CrudUtil.execute(
                sql,
                order.getOrderID()
        );
        return result;
    }
    public String generateNewID() throws SQLException {
        String sql="SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    public String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("o");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "o" + id;
        }
        return "o10000";
    }
    public BuyerDTO searchBuyer(String id) throws SQLException {
        String sql = "SELECT BuyerID FROM Orders WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);
        if (resultSet.next()){
            String buyerId=resultSet.getString(1);
            BuyerDTO buyerDTO =new BuyerDAOImpl().search(buyerId);
            return buyerDTO;
        }
        return null;
    }
}
