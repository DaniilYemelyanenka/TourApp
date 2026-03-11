package by.sysoev.tourApp.repository;


import by.sysoev.tourApp.DTO.TourScheduleDTO;

import java.util.List;

public interface TourScheduleRepository {
    List<TourScheduleDTO> getTourSchedulesById(Long id);

    void updateTourSchedule(TourScheduleDTO dto);
}
