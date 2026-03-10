package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.*;

import java.util.List;
import java.util.Optional;

public interface TourRepository {

    Optional<TourDTO> findTourInfo(Long tourId);

    List<TourBookingStatsDTO> bookingCountByTours();

    List<TourReviewDTO> getTourReviews();

    List<TourServicesDTO> getToursServices();

    List<TopTourDTO> getTop3Tours();

    List<ShortTour> getAllToursShortcut();

    void addTour(Long id,CreateTourDTO dto);

    void deleteTour(Long id);
}
