package lk.ijse.project_dkf.view.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialTM {
    private Date date;
    private Time time;
    private String oid;
    private int qty;
}
