package lk.ijse.project_dkf.bo.custom.impl;

import lk.ijse.project_dkf.bo.custom.OutputBO;
import lk.ijse.project_dkf.dao.DAOFactory;
import lk.ijse.project_dkf.dao.custom.OrderRatioDAO;
import lk.ijse.project_dkf.dao.custom.OrdersDAO;
import lk.ijse.project_dkf.dao.custom.OutputDAO;
import lk.ijse.project_dkf.dao.custom.impl.OrderRatioDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.project_dkf.dao.custom.impl.OutputDAOImpl;
import lk.ijse.project_dkf.dto.OutputDTO;
import lk.ijse.project_dkf.entity.Output;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutputBOImpl implements OutputBO {
    OutputDAO outputDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.OUTPUT);
    OrderRatioDAO orderRatioDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_RATIO);
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDERS);
    public boolean add(OutputDTO outputDTO) throws SQLException {
        return outputDAO.add(new Output(outputDTO.getOId(),outputDTO.getDate(),outputDTO.getTime(),outputDTO.getClId(),outputDTO.getSize(),outputDTO.getOut()));
    }

    public List<OutputDTO> getAll(String ids) throws SQLException {
        List<Output>outputs=outputDAO.getAll(ids);
        List<OutputDTO>outputDTOS=new ArrayList<>();
        for (Output o:outputs){
            outputDTOS.add(new OutputDTO(o.getOutputID(),o.getDate(),o.getTime(),o.getClothID(),o.getSize(),o.getDailyOut()));
        }
        return outputDTOS;
    }

    public boolean delete(OutputDTO outputDTO) throws SQLException {
        return outputDAO.delete(new Output(outputDTO.getOId(),outputDTO.getDate(),outputDTO.getTime(),outputDTO.getClId(),outputDTO.getSize(),outputDTO.getOut()));
    }

    public List<String> loadClothId(String id) throws SQLException {
        return orderRatioDAO.loadClothId(id);
    }

    public List<String> loadOrderIds() throws SQLException {
        return ordersDAO.loadOrderIds();
    }
}
