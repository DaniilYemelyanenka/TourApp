package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.entity.Passenger;
import by.sysoev.tourApp.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long addBooking(Long tourSchedule,Long userID,Double priceAtBooking) {

        return jdbcTemplate.queryForObject("""
                   INSERT INTO bookings
                   (tour_schedule_id,user_id,price_at_booking) VALUES (?,?,?)
                   RETURNING id
                   """
                ,Long.class,tourSchedule,userID,priceAtBooking);

    }

    @Override
    public void addBookingPassengers(Long bookingId, List<Passenger> passengers) {

        jdbcTemplate.batchUpdate("""
                INSERT INTO booking_passengers (booking_id,first_name,last_name)
                VALUES (?,?,?) 
                """,passengers, passengers.size(), (ps,passenger) -> {
                        ps.setLong(1,bookingId);
                        ps.setString(2, passenger.getFirstName());
                        ps.setString(3, passenger.getLastName());
        });
    }
}
