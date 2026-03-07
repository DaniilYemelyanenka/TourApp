package by.sysoev.tourApp.DTO;

import by.sysoev.tourApp.entity.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {

    private Long tourScheduleId;

    private Long userId;

    private Double priceAtBooking;

    private List<Passenger> passengers;
}
