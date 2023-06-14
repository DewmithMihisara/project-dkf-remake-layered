package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dto.OrderDTO;
import lk.ijse.project_dkf.dto.OrderRatioDTO;
import lk.ijse.project_dkf.view.tm.OrderRatioTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRatioDAOImpl implements OrderRatioDAO {
    public List<String> loadClothId(String id) throws SQLException {
        String sql="SELECT ClotheID FROM OrderRatio WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public OrderRatioDTO loadSize(String oId, String clID) throws SQLException {
        String sql="SELECT * FROM OrderRatio WHERE OrderID=? AND ClotheID=?";
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
        String sql = "INSERT INTO OrderRatio (OrderID,ClotheID,Disc,Colour,SQty,MQty,LQty,XLQty,XXLQty ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "SELECT ClotheID FROM OrderRatio ORDER BY ClotheID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    public String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("Cl-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Cl-" + id;
        }
        return "Cl-10000";
    }
    public String searchClothDetail(String selectedItem) throws SQLException {
        String sql = "SELECT Disc FROM OrderRatio WHERE ClotheID=?";
        ResultSet resultSet = CrudUtil.execute(sql,selectedItem);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
