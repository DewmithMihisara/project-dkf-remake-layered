package lk.ijse.project_dkf.dto;

import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogHistoryDTO {
    private String usrName;
    private LocalDateTime logIn;
    private LocalDateTime logOut;
}
