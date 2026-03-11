package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.TourScheduleDTO;
import by.sysoev.tourApp.service.TourScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tourSchedule")
@RequiredArgsConstructor
public class TourSchedulerControleer {

    private final TourScheduleService tourScheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TourScheduleDTO>> getTourScheduleDTO(@PathVariable Long id){
        List<TourScheduleDTO> list = tourScheduleService.getTourSchedule(id);
        return ResponseEntity.ok(list);
    }

    @PutMapping("me/update")
    public ResponseEntity<String> updateTourSchedule(@RequestBody TourScheduleDTO dto){
        tourScheduleService.updateTourSchedule(dto);
        return ResponseEntity.ok("success");
    }

}
