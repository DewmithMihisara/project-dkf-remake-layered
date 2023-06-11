package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRatio {
    private String OrderID;
    private String ClotheID;
    private String Disc;
    private String Colour;
    private int SQty;
    private int MQty;
    private int LQty;
    private int XLQty;
    private int XXLQty;


}
