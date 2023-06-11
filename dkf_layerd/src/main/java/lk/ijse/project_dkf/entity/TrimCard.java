package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrimCard {
    private String OrderId;
    private String TrimID;
    private String type;
    private String Colour;
    private int ReqQty;
}
