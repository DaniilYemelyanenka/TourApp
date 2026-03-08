package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientServiceRepositoryImpl implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<PaymentsStatsDTO> getClintPaymentsStats() {

        String sql = """
                SELECT 
                    u.first_name,
                    u.last_name,
                    cp.passport_number,
                    SUM(p.amount) AS total_spend
                FROM clint_profiles cp
                JOIN users u ON cp.user_id = u.id
                JOIN bookings b ON u.id = b.user_id
                JOIN payments p ON p.booking_id = b.id 
                GROUP BY u.first_name,u.last_name,cp.passport_number
                ORDER BY total_spend DESC
                """;


        return jdbcTemplate.query(sql,(rs,rowNum) -> new PaymentsStatsDTO(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("passport_number"),
                rs.getLong("total_spend")
        ));
    }
}
