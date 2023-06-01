package lk.ijse.project_dkf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class Stock {
    private String clthId;
    private String orderId;
    private String size;
    private int qty;
}
