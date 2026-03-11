package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.BookingDTO;
import by.sysoev.tourApp.DTO.BookingSeatsStatsDTO;
import by.sysoev.tourApp.DTO.ShorBookingDTO;
import by.sysoev.tourApp.entity.User;
import by.sysoev.tourApp.repository.UserRepository;
import by.sysoev.tourApp.repository.impl.BookingRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepositoryImpl bookingRepository;
    private final UserRepository userRepository;

    public Long makeBooking(BookingDTO bookingDTO,String username){
        Long bookingId = bookingRepository.addBooking(
                bookingDTO.getTourScheduleId(),
                username,
                bookingDTO.getPriceAtBooking()
                );

        bookingRepository.addBookingPassengers(bookingId,bookingDTO.getPassengers());
        return bookingId;
    }

    public List<BookingSeatsStatsDTO> getBookingSeatsStats() {
        return bookingRepository.getBookingSeatsStats();
    }

    public List<ShorBookingDTO> getUsersBookings(String username) {
       User user =  userRepository.getUserByEmail(username).get();
       return bookingRepository.getAllByUserId(user.getId());
    }
}
