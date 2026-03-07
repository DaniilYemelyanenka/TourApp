package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MostPopularCityDTO {
    private String cityName;
    private Integer bookingsCount;
}
