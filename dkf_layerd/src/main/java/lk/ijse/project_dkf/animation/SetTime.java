package lk.ijse.project_dkf.animation;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SetTime {
    public static void setTime(Label timeTxt) {
        Thread thread=new Thread(() -> {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("hh:mm:ss");
            while (true){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(()->{
                    timeTxt.setText(timeNow);
                });
            }
        });
        thread.start();
    }
}
