package lk.ijse.project_dkf.dto;

import lombok.*;

import java.sql.Time;
import java.util.Date;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackDTO {
    private String packID;
    private Date date;
    private Time time;
    private String clId;
    private String size;
    private int packQty;
}
