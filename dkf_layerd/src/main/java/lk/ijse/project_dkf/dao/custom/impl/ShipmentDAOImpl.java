package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.ShipmentDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;

public class ShipmentDAOImpl implements ShipmentDAO {
    public boolean add(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        String sql = "INSERT INTO ShipmentDTO (OrderID,buyerName , buyerAdd, ClotheId, size, Qty, Dates, Detail ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int routs=0;
        for (int i = 0; i < shipmentDTOS.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    shipmentDTOS.get(i).getOid(),
                    shipmentDTOS.get(i).getBuyerName(),
                    shipmentDTOS.get(i).getBuyerAddress(),
                    shipmentDTOS.get(i).getClid(),
                    shipmentDTOS.get(i).getSize(),
                    shipmentDTOS.get(i).getQty(),
                    shipmentDTOS.get(i).getDate(),
                    shipmentDTOS.get(i).getDesc()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs== shipmentDTOS.size()){
            return true;
        }
        return false;
    }
}
