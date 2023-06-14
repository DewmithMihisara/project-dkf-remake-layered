package lk.ijse.project_dkf.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dao.custom.TrimCardDAO;
import lk.ijse.project_dkf.view.tm.TrimCardTM;
import lk.ijse.project_dkf.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrimCardDAOImpl implements TrimCardDAO {
    public List<String> loadMaterialId(String id) throws SQLException {
        String sql="SELECT TrimID FROM TrimCard WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException {
        String sql ="INSERT INTO TrimCard (OrderID,TrimID,type,Colour,ReqQty ) VALUES(?, ?, ?, ?, ?)";
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
        String sql="SELECT TrimID FROM TrimCard ORDER BY TrimID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
   public String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("Tr-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Tr-" + id;
        }
        return "Tr-10000";
    }
}
