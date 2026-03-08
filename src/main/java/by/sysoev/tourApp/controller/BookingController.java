package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.BookingDTO;
import by.sysoev.tourApp.DTO.BookingSeatsStatsDTO;
import by.sysoev.tourApp.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> booking(@RequestBody BookingDTO bookingDTO){
        log.info("create booking request for user_id: {}, tour_id: {}",bookingDTO.getUserId(),bookingDTO.getTourScheduleId());
        bookingService.makeBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("seats/stats")
    public ResponseEntity<List<BookingSeatsStatsDTO>> getBookingSeatsStats(){
        log.info("Request for booking seats stats");
        List<BookingSeatsStatsDTO> seatsList = bookingService.getBookingSeatsStats();
        return ResponseEntity.status(HttpStatus.OK).body(seatsList);
    }
}
