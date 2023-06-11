package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.util.CrudUtil;

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
    public boolean add(OrderDTO orderDTO) throws SQLException {
        String sql ="INSERT INTO Orders (OrderID,BuyerID,Dedline,TtlQty,DailyOutQty,PayTerm,OrderDate ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                orderDTO.getOrderId(),
                orderDTO.getCompId(),
                orderDTO.getDline(),
                orderDTO.getTtlQty(),
                orderDTO.getDailyOut(),
                orderDTO.getPayment(),
                orderDTO.getOrderDate()
        );
    }

    @Override
    public List<OrderDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<OrderDTO> getAll(String s) throws SQLException {
        return null;
    }

    public boolean delete(OrderDTO orderDTO) throws SQLException {
        String sql="DELETE FROM Orders WHERE OrderID=? ";
        boolean result = CrudUtil.execute(
                sql,
                orderDTO.getOrderId()
        );
        return result;
    }
    public String generateNewID() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("Or0-", "")) + 1;
            return String.format("Or0-%03d", newCustomerId);
        } else {
            return "Or0-001";
        }
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