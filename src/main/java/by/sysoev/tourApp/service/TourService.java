package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.*;
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

    public List<TourServicesDTO> getTourServices(){
        return tourRepository.getToursServices();
    }
    public List<TopTourDTO> getTop3Tours(){
        return tourRepository.getTop3Tours();
    }

}
