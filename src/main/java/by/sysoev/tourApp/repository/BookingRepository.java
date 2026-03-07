package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.entity.Passenger;

import java.util.List;

public interface BookingRepository {
    Long addBooking(Long tourSchedule,Long userID,Double priceAtBooking);
    void addBookingPassengers(Long BookingId, List<Passenger> passengers);
}
