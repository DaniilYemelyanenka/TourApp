package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JdbcTemplate template;

    public Double getBookingTotoalSumm(Long id) {
        return template.queryForObject("SELECT calculate_total_booking(?)",Double.class,id);
    }
}
