package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.PackingDAO;
import lk.ijse.project_dkf.dto.PackDTO;
import lk.ijse.project_dkf.tm.PackingTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackingDAOImpl implements PackingDAO {
    public List<PackDTO> getAll(String packId) throws SQLException {
        String sql = "SELECT * FROM Packing WHERE PackID =?";
        ResultSet resultSet = CrudUtil.execute(sql,packId);

        ArrayList<PackDTO> packDTOS =new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            Date date=resultSet.getDate(2);
            Time time=resultSet.getTime(3);
            String clr=resultSet.getString(4);
            String size=resultSet.getString(5);
            int qty=resultSet.getInt(6);

            PackDTO packDTO =new PackDTO(id, date,time, clr,size, qty);
            packDTOS.add(packDTO);
        }
        return packDTOS;
    }
    public boolean add(PackDTO packDTO) throws SQLException {
        String sql ="INSERT INTO Packing (PackID, Date, Time, ClotheID, Size, PackQty ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                packDTO.getPackID(),
                packDTO.getDate(),
                packDTO.getTime(),
                packDTO.getClId(),
                packDTO.getSize(),
                packDTO.getPackQty()
        );
    }

    @Override
    public List<PackDTO> getAll() throws SQLException {
        return null;
    }

    public boolean delete(PackDTO packDTO) throws SQLException {
        String sql="DELETE FROM Packing WHERE PackID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                packDTO.getPackID(),
                packDTO.getDate(),
                packDTO.getTime()
        );
        return result;
    }
}
