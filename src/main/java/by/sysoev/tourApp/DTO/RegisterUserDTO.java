package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
