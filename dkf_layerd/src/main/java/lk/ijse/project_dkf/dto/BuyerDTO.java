package lk.ijse.project_dkf.dto;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BuyerDTO {
    private String buyerId;
    private String buyerName;
    private String buyerCn;
    private String buyerAddress;
}
