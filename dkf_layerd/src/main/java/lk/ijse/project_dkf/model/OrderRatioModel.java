package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class OrderRatioModel {

    public static boolean addRatio(ObservableList<OrderRatioTM> order, String id) throws SQLException {
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
    public static String getNextOrderRatioID() throws SQLException {
        String sql = "SELECT ClotheID FROM OrderRatio ORDER BY ClotheID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("Cl-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Cl-" + id;
        }
        return "Cl-10000";
    }
}
