package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.service.TourOperatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/tourOperator")
@RequiredArgsConstructor
public class TourOperatorController {

    private final TourOperatorService tourOperatorService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<TourOperatorStatsDTO>> getTourOperatorsStats(){
        log.info("Request for tour operators statistics ");
        List<TourOperatorStatsDTO> stats = tourOperatorService.getTourOperatorsStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }
}
