package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopTourDTO {
    private String tourName;
    private Integer bookingCount;
    private Integer rank;
}
