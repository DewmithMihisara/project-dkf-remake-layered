package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String OrderID;
    private String BuyerID;
    private Date Deadline;
    private int TtlQty;
    private int DailyOutQty;
    private String PayTerm;
    private Date OrderDate;
}
