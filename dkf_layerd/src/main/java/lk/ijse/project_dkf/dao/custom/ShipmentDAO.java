package lk.ijse.project_dkf.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.SuperDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.entity.Shipment;

import java.sql.SQLException;
import java.util.List;

public interface ShipmentDAO extends SuperDAO {
    boolean add(List<Shipment> shipmentDTOS) throws SQLException ;
}
