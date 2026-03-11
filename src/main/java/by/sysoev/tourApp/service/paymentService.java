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
}
