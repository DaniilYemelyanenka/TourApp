package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.TourScheduleDTO;
import by.sysoev.tourApp.repository.impl.TourScheduleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourScheduleService {

    private final TourScheduleRepositoryImpl repository;

    public List<TourScheduleDTO> getTourSchedule(Long tourId){
        return repository.getTourSchedulesById(tourId);
    }

    public void updateTourSchedule(TourScheduleDTO dto) {
        repository.updateTourSchedule(dto);
    }
}
