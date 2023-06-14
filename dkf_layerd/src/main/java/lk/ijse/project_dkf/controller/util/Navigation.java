package lk.ijse.project_dkf.controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class Navigation {
    private static AnchorPane root;
    public static void navigation(Rout rout,AnchorPane root) throws IOException {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();
        switch (rout) {
            case LOGIN -> initUi("logInForm.fxml");
            case NEW_AC -> initUi("newAcForm.fxml");
            case MAIN_DASHBOARD -> initUi("mainDashBoard.fxml");
            case DASHBOARD -> initUi("dashboardForm.fxml");
            case USER_SETTINGS -> initUi("settingForm.fxml");
            case ORDER -> initUi("orderForm.fxml");
            case NEW_ORDER -> initUi("newOrderForm.fxml");
            case BUYER -> initUi("buyerForm.fxml");
            case OUTPUT -> initUi("outputForm.fxml");
            case PAKING -> initUi("packingForm.fxml");
            case CUT_IN -> initUi("cutInputForm.fxml");
            case MATERIAL_IN -> initUi("materialInputForm.fxml");
            case ORDER_RATIO -> initUi("orderRatioForm.fxml");
            case TRIM_CARD -> initUi("trimCardForm.fxml");
            case SHIP -> initUi("shipingForm.fxml");
            case PASSWORD -> initUi("passwordForm.fxml");
            case GMAIL -> initUi("gmailConfirmForm.fxml");
            case FORGOT_PASS -> initUi("fogotPwForm.fxml");
            case PASSWORD_SETTING -> initUi("passwordSettingForm.fxml");
        }
    }
    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/" + location))));
    }
}


