package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.TourBookingStatsDTO;
import by.sysoev.tourApp.DTO.TourDTO;
import by.sysoev.tourApp.DTO.TourReviewDTO;
import by.sysoev.tourApp.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTour(@PathVariable Long id){
        TourDTO tour = tourService.getTourInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tour);
    }

    @GetMapping("stats")
    public ResponseEntity<List<TourBookingStatsDTO>> getTourBookingStats(){
        List<TourBookingStatsDTO> stats = tourService.getToursBookingStats();
        return ResponseEntity.status(HttpStatus.FOUND).body(stats);
    }

    @GetMapping("reviews")
    public ResponseEntity<List<TourReviewDTO>> getTourReviews(){
        List<TourReviewDTO> reviews = tourService.getTourReviews();
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
}
