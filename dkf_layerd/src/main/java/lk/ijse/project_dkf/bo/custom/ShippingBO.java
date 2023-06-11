package lk.ijse.project_dkf.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.ShipmentDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.StockDAOImpl;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.ShipmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface ShippingBO extends SuperBO {
    BuyerDTO searchBuyer(String id) throws SQLException;

    int searchAvailability(String id, String size) throws SQLException;

    String searchClothDetail(String selectedItem) throws SQLException;

    boolean add(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException;
    boolean shipmentPlace(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException;
    List<String> loadClothId(String id) throws SQLException;
    List<String> loadOrderIds() throws SQLException;
    boolean update(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException;
}
