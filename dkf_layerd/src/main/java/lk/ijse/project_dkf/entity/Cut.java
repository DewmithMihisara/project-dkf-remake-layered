package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cut {
    private String OrderID;
    private String ClotheID;
    private Date Date;
    private Time Time;
    private String CutQty;
    private String Type;
    private String Size;
}
