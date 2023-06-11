package lk.ijse.project_dkf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.project_dkf.bo.custom.LogInBO;
import lk.ijse.project_dkf.bo.custom.impl.LogInBOImpl;
import lk.ijse.project_dkf.controller.LogInFormController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Launcher extends Application {
    LogInBO logInBO=new LogInBOImpl();
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/logInForm.fxml")));
        Image image=new Image("/img/systemLogo/shirt.png");
        stage.getIcons().add(image);
        stage.setScene(new Scene(parent));
        stage.setTitle("DKF");
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setOnCloseRequest(we->{
            if(LogInFormController.logHistoryDTO !=null){
                LogInFormController.logHistoryDTO.setLogOut(LocalDateTime.now());
                try {
                    logInBO.save(LogInFormController.logHistoryDTO);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        stage.show();
    }
}
