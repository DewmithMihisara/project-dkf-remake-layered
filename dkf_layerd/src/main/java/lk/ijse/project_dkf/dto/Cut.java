package lk.ijse.project_dkf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Cut {
    private String cutID;
    private String clothId;
    private Date date;
    private Time time;
    private int cutQty;
    private String type;
    private String size;

}
