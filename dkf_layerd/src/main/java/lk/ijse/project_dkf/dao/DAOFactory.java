package lk.ijse.project_dkf.dao;

import lk.ijse.project_dkf.dao.custom.impl.*;

public class DAOFactory {
    private DAOFactory(){}
    private static DAOFactory daoFactory;
    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        BUYER, CUT, LOG_HISTORY, MATERIAL, ORDER_RATIO, ORDERS, OUTPUT, PACKING, SHIPMENT, STOCK, TRIM_CARD, USER
    }
    public  <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
            case BUYER -> {
                return (T)new BuyerDAOImpl();
            }
            case CUT -> {
                return (T)new CutDAOImpl();
            }
            case LOG_HISTORY -> {
                return (T)new LogHistoryDAOImpl();
            }
            case MATERIAL -> {
                return (T)new MaterialDAOImpl();
            }
            case ORDER_RATIO -> {
                return (T)new OrderRatioDAOImpl();
            }
            case ORDERS -> {
                return (T)new OrdersDAOImpl();
            }
            case OUTPUT -> {
                return (T)new OutputDAOImpl();
            }
            case PACKING -> {
                return (T)new PackingDAOImpl();
            }
            case SHIPMENT -> {
                return (T)new ShipmentDAOImpl();
            }
            case STOCK -> {
                return (T)new StockDAOImpl();
            }
            case TRIM_CARD -> {
                return (T)new TrimCardDAOImpl();
            }
            case USER -> {
                return (T)new UserDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }

}
