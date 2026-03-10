package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.LastBookingUsersDTO;
import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.DTO.UpdateClientInfoDTO;
import by.sysoev.tourApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<PaymentsStatsDTO> getClintPaymentsStats() {

        String sql = """
                SELECT 
                    u.first_name,
                    u.last_name,
                    cp.passport_number,
                    SUM(p.amount) AS total_spend
                FROM client_profiles cp
                JOIN users u ON cp.user_id = u.id
                JOIN bookings b ON u.id = b.user_id
                JOIN payments p ON p.booking_id = b.id 
                GROUP BY u.first_name,u.last_name,cp.passport_number
                ORDER BY total_spend DESC;
                """;

        log.debug("Select payments statistic.");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new PaymentsStatsDTO(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("passport_number"),
                rs.getLong("total_spend")
        ));
    }

    @Override
    public List<LastBookingUsersDTO> getLastBookingUsers() {
        String sql = """
                WITH last_booking AS (
                    SELECT 
                        b.user_id,
                        MAX(b.created_at) as last_booking_date
                    FROM bookings b
                    GROUP BY b.user_id
                )
                SELECT
                    u.first_name,
                    u.last_name,
                    lb.last_booking_date
                FROM last_booking lb
                JOIN users u ON u.id = lb.user_id
                ORDER BY lb.last_booking_date DESC;
                """;

        log.debug("Select last bookings for users.");

        return jdbcTemplate.query(sql,(rs,rowNum) -> new LastBookingUsersDTO(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("last_booking_date")
        ));
    }

    @Override
    public void updateInfo(Long userId, UpdateClientInfoDTO dto) {

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

        String updateClientSql = """
        UPDATE client_profiles
        SET passport_number = ?, date_of_birth = ?
        WHERE user_id = ?
        """;

        jdbcTemplate.update(
                updateClientSql,
                dto.getPassportNumber(),
                dto.getDateOfBirth(),
                userId
        );
    }
}
