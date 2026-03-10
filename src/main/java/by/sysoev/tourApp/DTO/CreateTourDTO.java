package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTourDTO {
    private String name;
    private Long hotelId;
    private Integer tourTypeId;
    private Integer transportId;
}
