package lk.ijse.project_dkf.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderRatioDTO {
    private String orderId;
    private String clotheId;
    private String disc;
    private String colour;
    private int sQty;
    private int mQty;
    private int lQty;
    private int xlQty;
    private int xxlty;

}
