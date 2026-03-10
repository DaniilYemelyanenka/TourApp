package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.*;
import by.sysoev.tourApp.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TourRepositoryImpl implements TourRepository {

    private final JdbcTemplate jdbcTemplate;


    //сложный запрос для полуения полной информации по туру
    @Override
    public Optional<TourDTO> findTourInfo(Long tourId) {

        String sql = """
                SELECT 
                    t.name AS tour_name,
                    l.city_name AS city_name,
                    c.name AS climate_name,
                    h.name AS hotel_name,
                    h.stars,
                    tt.type AS tour_type,
                    tr.name AS transport_name
                FROM tours t
                JOIN hotels h ON t.hotel_id = h.id
                JOIN locations l ON h.location_id = l.id
                JOIN climats c ON l.climate_id = c.id
                JOIN tour_types tt ON t.tour_type_id = tt.id
                JOIN transport tr ON t.transport_id = tr.id   
                WHERE t.id = ?;
                """;

        log.debug("Select  full tour information by tour id");

        TourDTO tour = jdbcTemplate.queryForObject(sql, (rs,rowNum) ->
            new TourDTO(
                    rs.getString("tour_name"),
                    rs.getString("city_name"),
                    rs.getString("climate_name"),
                    rs.getString("hotel_name"),
                    rs.getInt("stars"),
                    rs.getString("tour_type"),
                    rs.getString("transport_name")
            ),tourId);
        return Optional.ofNullable(tour);
    }

    //сложный запрос для получения списка(название тура - кколичество бронирований)
    @Override
    public List<TourBookingStatsDTO> bookingCountByTours() {
        String sql = """
                SELECT
                    t.name AS tour_name,
                    COUNT(b.id) AS total_bookings
                FROM tours t
                JOIN tour_schedule ts ON t.id = ts.tour_id
                JOIN bookings b ON ts.id = b.tour_schedule_id
                GROUP BY t.name
                ORDER BY total_bookings DESC;
                """;

        log.debug("Select tours and they bookings stats");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourBookingStatsDTO(
                rs.getString("tour_name"),
                rs.getInt("total_bookings")
        ));
    }

    @Override
    public List<TourReviewDTO> getTourReviews() {

        String sql = """
                SELECT 
                    t.name AS tour_name,
                    COUNT(tr.id) AS tour_reviews,
                    AVG(tr.mark) AS avg_rating
                FROM tours t
                LEFT JOIN tour_reviews tr ON t.id =  tr.tour_id
                GROUP BY t.name
                ORDER BY avg_rating DESC;
                """;

        log.debug("select tour  names with they reviews");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourReviewDTO(
                rs.getString("tour_name"),
                rs.getInt("tour_reviews"),
                rs.getDouble("avg_rating")
        ));
    }

    @Override
    public List<TourServicesDTO> getToursServices() {
        String sql = """
                SELECT
                    t.name AS tour_name,
                    STRING_AGG(s.name, ', ') AS services
                FROM tours t 
                JOIN tour_services ts ON t.id = ts.tour_id
                        JOIN services s ON ts.service_id = s.id
                GROUP BY t.name
                ORDER BY t.name;
                """;

        log.debug("Select tours and they services (services aggregates to one line by separator)");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourServicesDTO(
                rs.getString("tour_name"),
                rs.getString("services")
        ));
    }

    @Override
    public List<TopTourDTO> getTop3Tours() {
        String sql  = """
                WITH tour_counts AS (
                    SELECT
                        t.id,
                        t.name AS tour_name,
                        COUNT(b.id) AS booking_count
                    FROM tours t
                    JOIN tour_schedule ts ON t.id = ts.tour_id
                    JOIN bookings b ON ts.id = b.tour_schedule_id
                    GROUP BY t.id, t.name
                )
                SELECT
                    tour_name,
                    booking_count,
                    RANK() OVER (ORDER BY booking_count DESC) AS popularity_rank
                FROM tour_counts
                ORDER BY popularity_rank
                LIMIT 3;
                """;

        log.debug("Select top tours by bookings limit 3");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new TopTourDTO(
                rs.getString("tour_name"),
                rs.getInt("booking_count"),
                rs.getInt("popularity_rank")
        ));
    }

    @Override
    public List<ShortTour> getAllToursShortcut() {
        String sql = "SELECT id,name,rating FROM tours";
        return jdbcTemplate.query(sql,(rs,rowNum) -> new ShortTour(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("rating")
        ));
    }

    @Override
    public void addTour(Long id, CreateTourDTO dto) {
        String sql =  "INSERT INTO tours (name,hotel_id,tour_type_id,transport_id,tour_operator_id) VALUES (?,?,?,?,?)";
        int row  = jdbcTemplate.update(sql,dto.getName(),dto.getHotelId(),dto.getTourTypeId(),dto.getTransportId(),id);

        if(row == 0) throw new RuntimeException("tour is not added");
    }

    @Override
    public void deleteTour(Long id) {
        String sql = "DELETE FROM tours WHERE id = ?;";
        jdbcTemplate.update(sql,id);
    }


}
