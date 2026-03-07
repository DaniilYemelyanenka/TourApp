package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourDTO {
    private String tourName;
    private String cityName;
    private String climate;
    private String hotelName;
    private Integer stars;
    private String tourType;
    private String transport;
}
