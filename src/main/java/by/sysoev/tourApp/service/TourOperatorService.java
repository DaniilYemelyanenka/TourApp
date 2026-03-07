package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.repository.impl.TourOperatorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourOperatorService {

    private final TourOperatorRepositoryImpl tourOperatorRepository;

    public List<TourOperatorStatsDTO> getTourOperatorsStats(){
        return tourOperatorRepository.getTourOperatorsStats();
    }
}
