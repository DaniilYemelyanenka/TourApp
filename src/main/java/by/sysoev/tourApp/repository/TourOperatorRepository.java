package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.DTO.UpdateTourOperatorDTO;

import java.util.List;

public interface TourOperatorRepository {

    List<TourOperatorStatsDTO> getTourOperatorsStats();

    void updateTourOperator(Long userId,UpdateTourOperatorDTO dto);
}
