package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;

import java.util.List;

public interface TourOperatorRepository {

    List<TourOperatorStatsDTO> getTourOperatorsStats();

}
