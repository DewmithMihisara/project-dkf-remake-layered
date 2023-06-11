package lk.ijse.project_dkf.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.SuperDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;

import java.sql.SQLException;

public interface ShipmentDAO extends SuperDAO {
    boolean add(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException ;
}
