package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourBookingStatsDTO {
    private String tourName;
    private Integer tourBookings;
}
