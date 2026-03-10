package by.sysoev.tourApp.DTO;

import lombok.Data;

@Data
public class UpdateTourOperatorDTO extends UpdateUserDTO {
    private String companyName;
    private String description;
}
