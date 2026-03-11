package by.sysoev.tourApp.service;

import by.sysoev.tourApp.repository.impl.PaymentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class paymentService {

    private final PaymentRepositoryImpl repository;

    public Double getBookingTotalSumm(Long id) {
        return repository.getBookingTotoalSumm(id);
    }

    public void createPayment(Long id) {
        Double totalSumm = getBookingTotalSumm(id);
        repository.createPayment(id,totalSumm);
    }

    public void confirmPayment(Long bookingId) {
        repository.confirmPayment(bookingId);
    }
}
