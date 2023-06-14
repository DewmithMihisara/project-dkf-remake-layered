package lk.ijse.project_dkf.entity;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cut {
    private String OrderID;
    private String ClotheID;
    private Date Date;
    private Time Time;
    private String CutQty;
    private String Type;
    private String Size;
}
