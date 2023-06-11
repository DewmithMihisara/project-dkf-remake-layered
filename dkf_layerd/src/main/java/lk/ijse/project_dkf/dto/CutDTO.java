package lk.ijse.project_dkf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Date;
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class CutDTO {
    private String cutID;
    private String clothId;
    private String orderId;
    private Date date;
    private Time time;
    private int cutQty;
    private String type;
    private String size;

    public CutDTO(String clothId, String orderId, Date date, Time time, String type, String size) {
        this.clothId = clothId;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.size = size;
    }

    public CutDTO(String cutID, String clothId, Date date, Time time, int cutQty, String type, String size) {
        this.cutID = cutID;
        this.clothId = clothId;
        this.date = date;
        this.time = time;
        this.cutQty = cutQty;
        this.type = type;
        this.size = size;
    }

}
