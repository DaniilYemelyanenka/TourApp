package by.sysoev.tourApp.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateClientInfoDTO extends UpdateUserDTO{
    private String passportNumber;
    private Date dateOfBirth;
}
