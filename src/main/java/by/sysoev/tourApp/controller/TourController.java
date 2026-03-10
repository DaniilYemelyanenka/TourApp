package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.*;
import by.sysoev.tourApp.entity.UserPrincipals;
import by.sysoev.tourApp.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTour(@PathVariable Long id, @AuthenticationPrincipal UserPrincipals userPrincipals){
        log.info("Request for getting full information about tour with id: {}, bu user: {}",id,userPrincipals.getUsername());
        TourDTO tour = tourService.getTourInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tour);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats")
    public ResponseEntity<List<TourBookingStatsDTO>> getTourBookingStats(){
        log.info("Request for tours bookings stats");
        List<TourBookingStatsDTO> stats = tourService.getToursBookingStats();
        return ResponseEntity.status(HttpStatus.FOUND).body(stats);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reviews")
    public ResponseEntity<List<TourReviewDTO>> getTourReviews(){
        log.info("Request for tours reviews");
        List<TourReviewDTO> reviews = tourService.getTourReviews();
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/services")
    public ResponseEntity<List<TourServicesDTO>> getToursServices(){
        log.info("Request for tours services ");
        List<TourServicesDTO> tourServicesList = tourService.getTourServices();
        return ResponseEntity.status(HttpStatus.OK).body(tourServicesList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/top")
    public ResponseEntity<List<TopTourDTO>> getTop3Tour(@AuthenticationPrincipal UserPrincipals userPrincipals){
        log.info("Request for top 3 tours by bookings by username: {}",userPrincipals.getUsername());
        List<TopTourDTO> topList = tourService.getTop3Tours();
        return ResponseEntity.status(HttpStatus.OK).body(topList);
    }
}
