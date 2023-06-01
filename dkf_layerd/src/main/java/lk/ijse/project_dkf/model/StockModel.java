package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dto.Shipment;
import lk.ijse.project_dkf.dto.Stock;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;

public class StockModel {
    public static boolean add(Stock stock) throws SQLException {
        String sql = "INSERT INTO Stock (ClotheID, OrderId, size, qty) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                stock.getClthId(),
                stock.getOrderId(),
                stock.getSize(),
                stock.getQty()
        );
    }
    public static boolean update(ObservableList<Shipment> shipments) throws SQLException {
        String sql = "UPDATE Stock SET qty = ? WHERE ClotheID = ? AND size=? AND OrderId=?";
        int routs=0;
        for (int i = 0; i < shipments.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    shipments.get(i).getQty(),
                    shipments.get(i).getClid(),
                    shipments.get(i).getSize(),
                    shipments.get(i).getOid()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs==shipments.size()){
            return true;
        }
        return false;
    }
}
