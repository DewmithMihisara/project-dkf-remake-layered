package lk.ijse.project_dkf.animation;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class ShakeTextAnimation {
    public static void ShakeText(TextField text){
        text.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        new animatefx.animation.Shake(text).play();
    }
    public static void ShakeText(ComboBox text){
        text.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        new animatefx.animation.Shake(text).play();
    }
    public static void ShakeText(DatePicker text){
        text.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-width: 2px ;" +
                        "-fx-background-color: tranceparent ;" +
                        "-fx-text-fill : white;"
        );
        new animatefx.animation.Shake(text).play();
    }

}
