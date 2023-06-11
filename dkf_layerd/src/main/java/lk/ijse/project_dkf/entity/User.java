package lk.ijse.project_dkf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String UserName;
    private String Password;
    private String UserEmail;
    private String UserContact;
    private String UserAddress;
    private String Position;
}
