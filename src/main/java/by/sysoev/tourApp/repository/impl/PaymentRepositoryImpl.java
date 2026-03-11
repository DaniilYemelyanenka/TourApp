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

    @Override
    public void createPayment(Long id, Double totalSumm) {
        String sql = "INSERT INTO payments(booking_id,amount) VALUES (?,?)";
        template.update(sql,id,totalSumm);
    }

    @Override
    public void confirmPayment(Long bookingId) {
        String sql = "UPDATE payments SET payment_status_id = ? WHERE booking_id = ?";
        template.update(sql,2,bookingId);
    }
}
