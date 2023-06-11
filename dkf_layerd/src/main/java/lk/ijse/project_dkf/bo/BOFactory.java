package lk.ijse.project_dkf.bo;

import lk.ijse.project_dkf.bo.custom.impl.*;

public class BOFactory {
    private BOFactory(){}
    private static BOFactory boFactory;
    public static BOFactory getBoFactory(){
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BO{
        BUYER, CUT, FORGOT_PW, GMAIL_CONFIRM, LOG_IN, MAIN_DASH_BOARD, MATERIAL, NEW_ORDER, ORDER, ORDER_RATIO, OUTPUT, PACKING, PASSWORD, PASSWORD_SETTING, SETTINGS, SHIPPING, TRIM_CARD
    }
    public <T extends SuperBO>T getBO(BO bo){
        switch (bo){
            case BUYER -> {
                return (T) new BuyerBOImpl();
            }
            case CUT -> {
                return (T) new CutBOImpl();
            }
            case FORGOT_PW -> {
                return (T) new ForgotPwBOImpl();
            }
            case GMAIL_CONFIRM -> {
                return (T) new GmailConfirmBOImpl();
            }
            case LOG_IN -> {
                return (T) new LogInBOImpl();
            }
            case MAIN_DASH_BOARD ->{
                return (T) new MainDashBoardBOImpl();
            }
            case MATERIAL -> {
                return (T) new MaterialBOImpl();
            }
            case NEW_ORDER -> {
                return (T) new NewOrderBOImpl();
            }
            case ORDER -> {
                return (T) new OrderBOImpl();
            }
            case ORDER_RATIO -> {
                return (T) new OrderRatioBOImpl();
            }
            case OUTPUT -> {
                return (T) new OutputBOImpl();
            }
            case PACKING -> {
                return (T) new PackingBOImpl();
            }
            case PASSWORD -> {
                return (T) new PasswordBOImpl();
            }
            case PASSWORD_SETTING -> {
                return (T) new PasswordSettingsBOImpl();
            }
            case SETTINGS -> {
                return (T) new SettingsBOImpl();
            }
            case SHIPPING -> {
                return (T) new ShippingBOImpl();
            }
            case TRIM_CARD -> {
                return (T) new TrimCardBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
