package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourOperatorStatsDTO {
    private String companyName;
    private Integer toursCount;
    private Integer bookingsCount;
    private Integer totalRevenue;
    private Integer avgPayments;
}
