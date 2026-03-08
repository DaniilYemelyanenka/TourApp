package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookingSeatsStatsDTO {
    private String tourName;
    private Date startDate;
    private Integer capacity;
    private Integer bookedPlaces;
    private Integer availablePlaces;
}
