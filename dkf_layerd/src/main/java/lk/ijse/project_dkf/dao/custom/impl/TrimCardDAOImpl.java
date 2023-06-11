package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.TrimCardDAO;
import lk.ijse.project_dkf.tm.TrimCardTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrimCardDAOImpl implements TrimCardDAO {
    public List<String> loadMaterialId(String id) throws SQLException {
        String sql="SELECT TrimID FROM TrimCardDTO WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException {
        String sql ="INSERT INTO TrimCardDTO (OrderID,TrimID,type,Colour,ReqQty ) VALUES(?, ?, ?, ?, ?)";
        int routs=0;
        for (int i = 0; i < trimCardObj.size(); i++) {
            TrimCardTM trimCard = trimCardObj.get(i);
            int rout =  CrudUtil.execute(
                    sql,
                    orderId,
                    trimCard.getId(),
                    trimCard.getType(),
                    trimCard.getClr(),
                    trimCard.getQty()
            )? 1 : 0;
            routs +=rout;
        }
        if (routs==trimCardObj.size()){
            return true;
        }
        return false;
    }
    public String generateNewID() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT TrimID FROM TrimCardDTO ORDER BY TrimID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("Tr0-", "")) + 1;
            return String.format("Tr0-%03d", newCustomerId);
        } else {
            return "Tr0-001";
        }
    }
}
