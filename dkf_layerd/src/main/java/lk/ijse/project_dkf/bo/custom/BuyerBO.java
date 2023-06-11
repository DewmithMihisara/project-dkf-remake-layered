package lk.ijse.project_dkf.bo.custom;

import lk.ijse.project_dkf.bo.SuperBO;
import lk.ijse.project_dkf.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.project_dkf.dto.BuyerDTO;

import java.sql.SQLException;
import java.util.List;

public interface BuyerBO extends SuperBO {
     boolean addBuyer(BuyerDTO buyerDTO) throws SQLException ;
     List<BuyerDTO> getAllBuyers() throws SQLException ;
     BuyerDTO searchBuyer(String id) throws SQLException ;
     boolean updateBuyer(BuyerDTO buyerDTO) throws SQLException ;
     boolean deleteBuyer(BuyerDTO buyer) throws SQLException ;
     String getNextOrderID() throws SQLException ;
}
