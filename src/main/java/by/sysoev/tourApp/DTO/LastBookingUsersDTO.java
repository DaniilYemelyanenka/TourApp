package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LastBookingUsersDTO {
    private String firstName;
    private String LastName;
    private Date bookingDate;
}
