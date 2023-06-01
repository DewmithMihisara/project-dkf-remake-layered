package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.Stock;

import java.sql.Connection;
import java.sql.SQLException;

public class StockPlaceModel {
    public static boolean add(Pack pack, Stock stock) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isPackAdd = PackingModel.add(pack);
            if (isPackAdd){
                boolean isStockAdd= StockModel.add(stock);
                if (isStockAdd){
                    System.out.println(stock);
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
