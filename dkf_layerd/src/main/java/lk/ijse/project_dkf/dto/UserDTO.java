package lk.ijse.project_dkf.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String password;
    private String userEmail;
    private String contact;
    private String address;
    private String position;
}