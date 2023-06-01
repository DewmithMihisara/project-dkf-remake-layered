package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.TrimCard;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.dto.tm.TrimCardTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrimCardModel {
    public static boolean addTrimCard(ObservableList<TrimCardTM> trimCardObj, String orderId) throws SQLException {
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
    public static String getNextTrimID() throws SQLException {
        String sql="SELECT TrimID FROM TrimCard ORDER BY TrimID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("Tr-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Tr-" + id;
        }
        return "Tr-10000";
    }
}

