package lk.ijse.project_dkf.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrimCard {
    private String orderId;
    private String trimId;
    private String typ;
    private String clr;
    private int qty;
}
