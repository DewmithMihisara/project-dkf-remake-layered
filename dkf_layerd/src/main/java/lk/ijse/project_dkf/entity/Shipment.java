package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private String OrderID;
    private String buyerName;
    private String buyerAdd;
    private String ClotheId;
    private String size;
    private int Qty;
    private Date Dates;
    private String Detail;
}
