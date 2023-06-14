package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.StockDAO;
import lk.ijse.project_dkf.dto.ShipmentDTO;
import lk.ijse.project_dkf.entity.Stock;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    public boolean add(Stock stock) throws SQLException {
        String sql = "INSERT INTO Stock (ClotheID, OrderId, size, qty) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                stock.getClotheID(),
                stock.getOrderId(),
                stock.getSize(),
                stock.getQty()
        );
    }

    @Override
    public List<Stock> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Stock> getAll(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Stock stock) throws SQLException {
        return false;
    }

    public boolean update(List<Stock> stocks) throws SQLException {
        String sql = "UPDATE Stock SET qty = ? WHERE ClotheID = ? AND size=? AND OrderId=?";
        int routs=0;
        for (int i = 0; i < stocks.size(); i++) {
            int rout =  CrudUtil.execute(
                    sql,
                    stocks.get(i).getQty(),
                    stocks.get(i).getClotheID(),
                    stocks.get(i).getSize(),
                    stocks.get(i).getOrderId()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs== stocks.size()){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ObservableList<ShipmentDTO> shipmentDTOS) throws SQLException {
        return false;
    }

    public int searchAvailability(String id, String size) throws SQLException {
        String sql = "SELECT qty FROM Stock WHERE ClotheID=? AND size=?";
        ResultSet resultSet = CrudUtil.execute(sql,id,size);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
