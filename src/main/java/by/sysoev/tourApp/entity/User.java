package by.sysoev.tourApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String passwordHash;
    private Set<Role> roles;

}
