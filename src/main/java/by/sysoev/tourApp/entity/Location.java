package by.sysoev.tourApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private Long id;
    private String cityName;
    private String climateName;
}
