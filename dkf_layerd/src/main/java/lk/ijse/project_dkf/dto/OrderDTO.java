package lk.ijse.project_dkf.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    private String compId;
    private Date dline;
    private int ttlQty;
    private int dailyOut;
    private String payment;
    private Date orderDate;
}
