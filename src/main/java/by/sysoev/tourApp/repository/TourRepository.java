package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.TourBookingStatsDTO;
import by.sysoev.tourApp.DTO.TourDTO;
import by.sysoev.tourApp.DTO.TourReviewDTO;

import java.util.List;
import java.util.Optional;

public interface TourRepository {

    Optional<TourDTO> findTourInfo(Long tourId);

    List<TourBookingStatsDTO> bookingCountByTours();

    List<TourReviewDTO> getTourReviews();
}
