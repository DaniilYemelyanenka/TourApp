package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.BookingDTO;
import by.sysoev.tourApp.DTO.BookingSeatsStatsDTO;
import by.sysoev.tourApp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> booking(@RequestBody BookingDTO bookingDTO){
        bookingService.makeBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping()
    public ResponseEntity<List<BookingSeatsStatsDTO>> getBookingSeatsStats(){
        List<BookingSeatsStatsDTO> seatsList = bookingService.getBookingSeatsStats();
        return ResponseEntity.status(HttpStatus.OK).body(seatsList);
    }
}
