package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.dto.OrderRatioDTO;
import lk.ijse.project_dkf.tm.OrderRatioTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRatioDAOImpl implements OrderRatioDAO {
    public List<String> loadClothId(String id) throws SQLException {
        String sql="SELECT ClotheID FROM OrderRatioDTO WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public OrderRatioDTO loadSize(String oId, String clID) throws SQLException {
        String sql="SELECT * FROM OrderRatioDTO WHERE OrderID=? AND ClotheID=?";
        ResultSet resultSet = CrudUtil.execute(sql,oId,clID);
        if (resultSet.next()){
            return new OrderRatioDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9)
            );
        }
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
    public boolean addRatio(ObservableList<OrderRatioTM> order, String id) throws SQLException {
        String sql = "INSERT INTO OrderRatioDTO (OrderID,ClotheID,Disc,Colour,SQty,MQty,LQty,XLQty,XXLQty ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int routs=0;
        for (int i = 0; i < order.size(); i++) {
            OrderRatioTM orderRatio = order.get(i);
            int rout =  CrudUtil.execute(
                    sql,
                    id,
                    orderRatio.getId(),
                    orderRatio.getDesc(),
                    orderRatio.getClr(),
                    orderRatio.getS(),
                    orderRatio.getM(),
                    orderRatio.getL(),
                    orderRatio.getXl(),
                    orderRatio.getXxl()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs==order.size()){
            return true;
        }
        return false;
    }
    public String generateNewID() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT ClotheID FROM OrderRatioDTO ORDER BY ClotheID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("Ra0-", "")) + 1;
            return String.format("Ra0-%03d", newCustomerId);
        } else {
            return "Ra0-001";
        }
    }
    public String searchClothDetail(String selectedItem) throws SQLException {
        String sql = "SELECT Disc FROM OrderRatioDTO WHERE ClotheID=?";
        ResultSet resultSet = CrudUtil.execute(sql,selectedItem);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
