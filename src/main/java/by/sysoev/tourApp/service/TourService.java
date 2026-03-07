package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.TourBookingStatsDTO;
import by.sysoev.tourApp.DTO.TourDTO;
import by.sysoev.tourApp.DTO.TourReviewDTO;
import by.sysoev.tourApp.repository.impl.TourRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepositoryImpl tourRepository;

    public TourDTO getTourInfoById(Long tourId){
        return tourRepository.findTourInfo(tourId).orElseThrow(() -> new RuntimeException("Tour Info not found"));
    }

    public List<TourBookingStatsDTO> getToursBookingStats(){
        return tourRepository.bookingCountByTours();
    }


    public List<TourReviewDTO> getTourReviews(){
        return tourRepository.getTourReviews();
    }

}
