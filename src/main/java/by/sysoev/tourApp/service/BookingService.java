package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.BookingDTO;
import by.sysoev.tourApp.DTO.BookingSeatsStatsDTO;
import by.sysoev.tourApp.repository.impl.BookingRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepositoryImpl bookingRepository;


    public void makeBooking(BookingDTO bookingDTO){
        Long bookingId = bookingRepository.addBooking(
                bookingDTO.getTourScheduleId(),
                bookingDTO.getUserId(),
                bookingDTO.getPriceAtBooking()
                );

        bookingRepository.addBookingPassengers(bookingId,bookingDTO.getPassengers());
    }

    public List<BookingSeatsStatsDTO> getBookingSeatsStats() {
        return bookingRepository.getBookingSeatsStats();
    }
}
