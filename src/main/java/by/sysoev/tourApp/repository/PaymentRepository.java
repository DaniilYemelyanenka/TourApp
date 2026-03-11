package by.sysoev.tourApp.repository;

public interface PaymentRepository {
    Double getBookingTotoalSumm(Long id);

    void createPayment(Long id, Double totalSumm);

    void confirmPayment(Long bookingId);
}
