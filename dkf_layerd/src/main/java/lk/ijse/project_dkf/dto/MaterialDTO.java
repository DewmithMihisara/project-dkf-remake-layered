package lk.ijse.project_dkf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
    private String orderID;
    private String mid;
    private Time time;
    private int qty;
    private Date date;
}
