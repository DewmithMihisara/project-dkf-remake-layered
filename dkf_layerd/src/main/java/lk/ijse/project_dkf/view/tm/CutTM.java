package lk.ijse.project_dkf.view.tm;

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
public class CutTM {
    private Date date;
    private Time time;
    private String clothID;
    private String size;
    private String type;
    private int qty;
}
