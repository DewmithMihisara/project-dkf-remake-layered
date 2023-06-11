package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.CutDAO;
import lk.ijse.project_dkf.dto.CutDTO;
import lk.ijse.project_dkf.tm.CutTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutDAOImpl implements CutDAO {
    public List<CutDTO> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM CutDTO WHERE OrderId =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<CutDTO> cutDTOS =new ArrayList<>();
        while (resultSet.next()){
            String cutID = resultSet.getString(1);
            String clId=resultSet.getString(2);
            Date date=resultSet.getDate(3);
            Time time=resultSet.getTime(4);
            int qty=resultSet.getInt(5);
            String type= resultSet.getString(6);
            String size=resultSet.getString(7);

            CutDTO cutDTO =new CutDTO(cutID, clId, date, time, qty, type, size);
            cutDTOS.add(cutDTO);
        }
        return cutDTOS;
    }

    @Override
    public List<CutDTO> getAll() throws SQLException {
        return null;
    }

    public boolean add(CutDTO cutDTO) throws SQLException {
        String sql ="INSERT INTO CutDTO (OrderId, ClotheID, Date, Time, CutQty, Type, Size ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                cutDTO.getCutID(),
                cutDTO.getClothId(),
                cutDTO.getDate(),
                cutDTO.getTime(),
                cutDTO.getCutQty(),
                cutDTO.getType(),
                cutDTO.getSize()
        );
    }

    public boolean delete(CutDTO cutDTO) throws SQLException {
        String sql="DELETE FROM CutDTO WHERE OrderId=? AND ClotheId=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                cutDTO.getOrderId(),
                cutDTO.getClothId(),
                cutDTO.getDate(),
                cutDTO.getTime()
        );
        return result;
    }

}
