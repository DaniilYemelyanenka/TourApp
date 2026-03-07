package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.service.TourOperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tourOperator")
@RequiredArgsConstructor
public class TourOperatorController {

    private final TourOperatorService tourOperatorService;

    @GetMapping()
    public ResponseEntity<List<TourOperatorStatsDTO>> getTourOperatorsStats(){
        List<TourOperatorStatsDTO> stats = tourOperatorService.getTourOperatorsStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }
}
