package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.Shipment;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipModel {
    public static Buyer searchBuyer(String id) throws SQLException {
        String sql = "SELECT BuyerID FROM Orders WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);
        if (resultSet.next()){
            String buyerId=resultSet.getString(1);
            Buyer buyer=searchBuyerDetail(buyerId);
            return buyer;
        }
        return null;
    }

    private static Buyer searchBuyerDetail(String buyerId) throws SQLException {
        String sql = "SELECT * FROM Buyer WHERE BuyerID=?";
        ResultSet resultSet = CrudUtil.execute(sql,buyerId);
        if (resultSet.next()){
            return new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public static int searchAvailability(String id, String size) throws SQLException {
        String sql = "SELECT qty FROM Stock WHERE ClotheID=? AND size=?";
        ResultSet resultSet = CrudUtil.execute(sql,id,size);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static String searchClothDetail(String selectedItem) throws SQLException {
        String sql = "SELECT Disc FROM OrderRatio WHERE ClotheID=?";
        ResultSet resultSet = CrudUtil.execute(sql,selectedItem);
        if (resultSet.next()){
           return resultSet.getString(1);
        }
        return null;
    }

    public static boolean add(ObservableList<Shipment> shipments) throws SQLException {
        String sql = "INSERT INTO Shipment (OrderID,buyerName , buyerAdd, ClotheId, size, Qty, Dates, Detail ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int routs=0;
        for (int i = 0; i < shipments.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    shipments.get(i).getOid(),
                    shipments.get(i).getBuyerName(),
                    shipments.get(i).getBuyerAddress(),
                    shipments.get(i).getClid(),
                    shipments.get(i).getSize(),
                    shipments.get(i).getQty(),
                    shipments.get(i).getDate(),
                    shipments.get(i).getDesc()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs==shipments.size()){
            return true;
        }
        return false;
    }
}
