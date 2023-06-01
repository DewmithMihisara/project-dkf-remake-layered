package lk.ijse.project_dkf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Shipment {
    private String oid;
    private String buyerName;
    private String buyerAddress;
    private String clid;
    private String size;
    private int qty;
    private Date date;
    private String desc;
}
