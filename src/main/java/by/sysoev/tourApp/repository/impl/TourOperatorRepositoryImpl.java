package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.repository.TourOperatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TourOperatorRepositoryImpl implements TourOperatorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TourOperatorStatsDTO> getTourOperatorsStats() {
        String sql = """
               SELECT
                    top.company_name,
                    COUNT(DISTINCT t.id) AS tours_count,
                    COUNT(DISTINCT b.id) AS bookings_count,
                    SUM(p.amount) AS total_revenue,
                    AVG(p.amount) AS avg_payment
               FROM tour_operator_profiles top
               JOIN tours t ON top.id = t.tour_operator_id
               JOIN tour_schedule ts ON t.id = ts.tour_id
               JOIN bookings b ON ts.id = b.tour_schedule_id
               JOIN payments p ON b.id = p.booking_id
               GROUP BY top.company_name
               ORDER BY total_revenue DESC;
               """;

        log.debug("Select statistic for tours and tour operators.");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourOperatorStatsDTO(
                rs.getString("company_name"),
                rs.getInt("tours_count"),
                rs.getInt("bookings_count"),
                rs.getInt("total_revenue"),
                rs.getInt("avg_payment")
        ));
    }
}
