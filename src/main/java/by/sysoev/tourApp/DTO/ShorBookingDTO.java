package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ShorBookingDTO {
    private String tourName;
    private Date startDate;
    private Date endDate;
    private String status;
}
