package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.DTO.UpdateTourOperatorDTO;
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

    @Override
    public void updateTourOperator(Long userId,UpdateTourOperatorDTO dto) {
        String updateUserSql = """
        UPDATE users
        SET first_name = ?, last_name = ?, phone = ?
        WHERE id = ?
        """;

        jdbcTemplate.update(
                updateUserSql,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                userId
        );


        String updateOperatorSql = """
        UPDATE tour_operator_profiles
        SET company_name = ?, description = ?
        WHERE user_id = ?
        """;

        jdbcTemplate.update(
                updateOperatorSql,
                dto.getCompanyName(),
                dto.getDescription(),
                userId
        );
    }

    @Override
    public UpdateTourOperatorDTO find(Long id) {
        String sql = """
                SELECT
                    top.company_name,
                    top.description,
                    u.first_name,
                    u.last_name,
                    u.phone
                FROM tour_operator_profiles top
                JOIN users u ON u.id = top.user_id
                WHERE top.id = ?;
                """;
        return jdbcTemplate.queryForObject(sql,(rs,rowNum) -> {
             UpdateTourOperatorDTO dto = new UpdateTourOperatorDTO();
             dto.setCompanyName(rs.getString("company_name"));
             dto.setDescription(rs.getString("description"));
             dto.setFirstName(rs.getString("first_name"));
             dto.setLastName(rs.getString("last_name"));
             dto.setPhone(rs.getString("phone"));
             return dto;
             },id);
    }
}
