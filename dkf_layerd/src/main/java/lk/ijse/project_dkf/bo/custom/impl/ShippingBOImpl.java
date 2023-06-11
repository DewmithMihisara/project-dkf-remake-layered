package lk.ijse.project_dkf.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import lk.ijse.project_dkf.bo.custom.ShippingBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.ShipmentDAO;
import lk.ijse.project_dkf.dao.custom.StockDAO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.ShipmentDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.StockDAOImpl;
import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.dto.StockDTO;
import lk.ijse.project_dkf.entity.Shipment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShippingBOImpl implements ShippingBO {
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    OrderRatioDAO orderRatioDAO =DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    StockDAO stockDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.STOCK);
    ShipmentDAO shipmentDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.SHIPMENT);

    public BuyerDTO searchBuyer(String id) throws SQLException {
        return ordersDAO.searchBuyer(id);
    }

    public int searchAvailability(String id, String size) throws SQLException {
        return stockDAO.searchAvailability(id, size);
    }

    public String searchClothDetail(String selectedItem) throws SQLException {
        return new OrderRatioDAOImpl().searchClothDetail(selectedItem);
    }

    public boolean add(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        List<Shipment>shipments=new ArrayList<>();
        for (ShipmentDTO s: shipmentDTOS){
            shipments.add(new Shipment(s.getOid(),s.getBuyerName(),s.getBuyerAddress(),s.getClid(),s.getSize(),s.getQty(),s.getDate(),s.getDesc()));
        }
        return shipmentDAO.add(shipments);
    }

    public List<String> loadClothId(String id) throws SQLException {
        return orderRatioDAO.loadClothId(id);
    }

    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }

    public boolean update(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        return stockDAO.update(shipmentDTOS);
    }

    public boolean shipmentPlace(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isStockUpdate = update(shipmentDTOS);
            if (isStockUpdate) {
                boolean isShipmentAdd = add(shipmentDTOS);
                if (isShipmentAdd) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
