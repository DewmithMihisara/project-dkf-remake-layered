package lk.ijse.project_dkf.dao.custom.impl;

import lk.ijse.project_dkf.dao.custom.MaterialDAO;
import lk.ijse.project_dkf.dto.MaterialDTO;
import lk.ijse.project_dkf.tm.MaterialTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    public boolean add(MaterialDTO materialDTO) throws SQLException {
        String sql ="INSERT INTO MaterialDTO (OrderID, MatID, Time, MaterialQty ,Date ) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                materialDTO.getOrderID(),
                materialDTO.getMid(),
                materialDTO.getTime(),
                materialDTO.getQty(),
                materialDTO.getDate()
        );
    }

    @Override
    public List<MaterialDTO> getAll() throws SQLException {
        return null;
    }

    public boolean delete(MaterialDTO materialDTO) throws SQLException {
        String sql="DELETE FROM MaterialDTO WHERE OrderId=? AND MatID=? AND Date=? AND Time=?";
        boolean result = CrudUtil.execute(
                sql,
                materialDTO.getOrderID(),
                materialDTO.getMid(),
                materialDTO.getDate(),
                materialDTO.getTime()
        );
        return result;
    }

    public List<MaterialDTO> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM MaterialDTO WHERE OrderID =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<MaterialDTO> materialDTOS =new ArrayList<>();
        while (resultSet.next()){
            String oId= resultSet.getString(1);
            String mId=resultSet.getString(2);
            Time time=resultSet.getTime(3);
            int qty=resultSet.getInt(4);
            Date date=resultSet.getDate(5);

            MaterialDTO materialDTO =new MaterialDTO(oId,mId,time,qty,date);
            materialDTOS.add(materialDTO);
        }
        return materialDTOS;
    }
}
