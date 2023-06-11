package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Output {
    private String OutputID;
    private Date Date;
    private Time Time;
    private String ClothID;
    private String size;
    private int DailyOut;
}
