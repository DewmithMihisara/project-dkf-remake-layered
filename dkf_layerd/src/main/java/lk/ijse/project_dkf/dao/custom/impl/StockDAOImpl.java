package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.StockDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.dto.StockDTO;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    public boolean add(StockDTO stockDTO) throws SQLException {
        String sql = "INSERT INTO StockDTO (ClotheID, OrderId, size, qty) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                stockDTO.getClthId(),
                stockDTO.getOrderId(),
                stockDTO.getSize(),
                stockDTO.getQty()
        );
    }

    @Override
    public List<StockDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<StockDTO> getAll(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(StockDTO dto) throws SQLException {
        return false;
    }

    public boolean update(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        String sql = "UPDATE StockDTO SET qty = ? WHERE ClotheID = ? AND size=? AND OrderId=?";
        int routs=0;
        for (int i = 0; i < shipmentDTOS.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    shipmentDTOS.get(i).getQty(),
                    shipmentDTOS.get(i).getClid(),
                    shipmentDTOS.get(i).getSize(),
                    shipmentDTOS.get(i).getOid()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs== shipmentDTOS.size()){
            return true;
        }
        return false;
    }
    public int searchAvailability(String id, String size) throws SQLException {
        String sql = "SELECT qty FROM StockDTO WHERE ClotheID=? AND size=?";
        ResultSet resultSet = CrudUtil.execute(sql,id,size);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
