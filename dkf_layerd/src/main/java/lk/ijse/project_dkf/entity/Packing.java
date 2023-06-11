package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Packing {
    private String PackID;
    private Date Date;
    private Time Time;
    private String ClotheID;
    private String Size;
    private int PackQty;
}
