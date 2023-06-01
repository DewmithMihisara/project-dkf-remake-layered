package lk.ijse.project_dkf.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class ShipmentTM {
    private String clthId;
    private String desc;
    private String size;
    private int qty;
}
