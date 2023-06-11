package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.BuyerBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.BuyerDAO;
import lk.ijse.project_dkf.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.project_dkf.dto.BuyerDTO;
import lk.ijse.project_dkf.entity.Buyer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerBOImpl implements BuyerBO {
    BuyerDAO buyerDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.BUYER);
    public boolean addBuyer(BuyerDTO buyerDTO) throws SQLException {
        return buyerDAO.add(new Buyer(buyerDTO.getBuyerId(),buyerDTO.getBuyerName(),buyerDTO.getBuyerCn(),buyerDTO.getBuyerAddress()));
    }

    @Override
    public List<BuyerDTO> getAllBuyers() throws SQLException {
        List<Buyer>all= buyerDAO.getAll();
        List<BuyerDTO>list=new ArrayList<>();
        for (Buyer b: all){
            list.add(new BuyerDTO(b.getBuyerID(),b.getBuyerName(),b.getBuyerCN(),b.getBuyerAddress()));
        }
        return list;
    }

    @Override
    public BuyerDTO searchBuyer(String id) throws SQLException {
        return buyerDAO.search(id);
    }

    @Override
    public boolean updateBuyer(BuyerDTO buyerDTO) throws SQLException {
        return buyerDAO.update(new Buyer(buyerDTO.getBuyerId(),buyerDTO.getBuyerName(),buyerDTO.getBuyerCn(),buyerDTO.getBuyerAddress()));
    }

    @Override
    public boolean deleteBuyer(BuyerDTO buyerDTO) throws SQLException {
        return buyerDAO.delete(new Buyer(buyerDTO.getBuyerId(),buyerDTO.getBuyerName(),buyerDTO.getBuyerCn(),buyerDTO.getBuyerAddress()));
    }
    public String getNextOrderID() throws SQLException {
        return buyerDAO.generateNewID();
    }
}
