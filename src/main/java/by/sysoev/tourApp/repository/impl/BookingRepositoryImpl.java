package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.BookingSeatsStatsDTO;
import by.sysoev.tourApp.entity.Passenger;
import by.sysoev.tourApp.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long addBooking(Long tourSchedule,Long userID,Double priceAtBooking) {
        log.debug("Insert booking for user with id:{} and tour schedule with id:{}",userID,tourSchedule);
        return jdbcTemplate.queryForObject("""
                   INSERT INTO bookings
                   (tour_schedule_id,user_id,price_at_booking) VALUES (?,?,?)
                   RETURNING id
                   """
                ,Long.class,tourSchedule,userID,priceAtBooking);

    }

    @Override
    public void addBookingPassengers(Long bookingId, List<Passenger> passengers) {
    log.debug("Insert booking passengers for booking with id:{}", bookingId);
        jdbcTemplate.batchUpdate("""
                INSERT INTO booking_passengers (booking_id,first_name,last_name)
                VALUES (?,?,?) 
                """,passengers, passengers.size(), (ps,passenger) -> {
                        ps.setLong(1,bookingId);
                        ps.setString(2, passenger.getFirstName());
                        ps.setString(3, passenger.getLastName());
        });
    }

    public List<BookingSeatsStatsDTO> getBookingSeatsStats() {
        String sql = """
                SELECT
                    t.name AS tour_name,
                    ts.start_date,
                    ts.capacity,
                    COUNT(bp.id) AS booked_places
                    ts.capacity - COUNT(bp.id) AS available_places
               FROM tour_schedule ts
               JOIN tours t ON ts.tour_id = t.id
               LEFT JOIN bookings b ON ts.id = b.tour_schedule_id
               LEFY JOIN booking_passengers bp ON b.id = bp.booking_id
               GROUP BY t.name,ts.start_date, ts.capacity
               ORDER BY ts.sstart_date;
               """;

        log.debug("Select booking seats stats");
        return jdbcTemplate.query(sql,(rs,rowNum) -> new BookingSeatsStatsDTO(
                rs.getString("tour_name"),
                rs.getDate("start_date"),
                rs.getInt("capacity"),
                rs.getInt("booked_places"),
                rs.getInt("available_places")
        ));
    }
}
