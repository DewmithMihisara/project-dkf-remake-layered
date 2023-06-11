package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.ShipmentDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.entity.Shipment;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ShipmentDAOImpl implements ShipmentDAO {
    public boolean add(List<Shipment> shipments) throws SQLException {
        String sql = "INSERT INTO ShipmentDTO (OrderID,buyerName , buyerAdd, ClotheId, size, Qty, Dates, Detail ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int routs=0;
        for (int i = 0; i < shipments.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    shipments.get(i).getOrderID(),
                    shipments.get(i).getBuyerName(),
                    shipments.get(i).getBuyerAdd(),
                    shipments.get(i).getClotheId(),
                    shipments.get(i).getSize(),
                    shipments.get(i).getQty(),
                    shipments.get(i).getDates(),
                    shipments.get(i).getDetail()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs== shipments.size()){
            return true;
        }
        return false;
    }
}
