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
public class Material {
    private String OrderID;
    private String MatID;
    private Time Time;
    private int MaterialQty;
    private Date Date;
}
