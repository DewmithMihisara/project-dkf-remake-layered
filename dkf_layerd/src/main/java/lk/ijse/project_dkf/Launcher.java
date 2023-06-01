package lk.ijse.project_dkf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.project_dkf.controller.LogInFormController;
import lk.ijse.project_dkf.model.LogHistoryModel;
import lk.ijse.project_dkf.util.Gmail;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class Launcher extends Application {
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
            if(LogInFormController.logHistory!=null){
                LogInFormController.logHistory.setLogOut(LocalDateTime.now());
                try {
                    LogHistoryModel.save(LogInFormController.logHistory);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        stage.show();
    }
}
