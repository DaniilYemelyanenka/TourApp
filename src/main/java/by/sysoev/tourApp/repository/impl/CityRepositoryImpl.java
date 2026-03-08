package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<MostPopularCityDTO> getMostPopularCities() {
        String sql = """
                SELECT 
                    l.city_name AS city_name,
                    COUNT(b.id) AS bookings_count
                FROM locations l
                JOIN hotels h ON l.id = h.location_id
                JOIN tours t ON h.id = t.hotel_id 
                JOIN tour_schedule ts ON t.id = ts.tour_id
                JOIN bookings b ON ts.id = b.tour_schedule_id
                GOUP BY l.city_name
                ORDER BY bookings_count DESC
                LIMIT 5;
                """;

        log.debug("Select most popular cities");
        return jdbcTemplate.query(sql,(rs,rowNum) -> new MostPopularCityDTO(
                rs.getString("city_name"),
                rs.getInt("bookings_count")
        ));
    }
}
