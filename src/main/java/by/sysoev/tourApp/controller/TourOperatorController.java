package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.DTO.UpdateTourOperatorDTO;
import by.sysoev.tourApp.entity.User;
import by.sysoev.tourApp.entity.UserPrincipals;
import by.sysoev.tourApp.service.TourOperatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/tourOperator")
@RequiredArgsConstructor
public class TourOperatorController {

    private final TourOperatorService tourOperatorService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("stats")
    public ResponseEntity<List<TourOperatorStatsDTO>> getTourOperatorsStats(){
        log.info("Request for tour operators statistics ");
        List<TourOperatorStatsDTO> stats = tourOperatorService.getTourOperatorsStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }

    @PutMapping("/me/update")
    public ResponseEntity<String> updateTourOperatorInfo(@AuthenticationPrincipal UserPrincipals userPrincipals,
                                                         @RequestBody UpdateTourOperatorDTO updateTourOperatorDTO){
        log.info("Request to update tourOperator: {} info",userPrincipals.getUsername());
        tourOperatorService.updateTourOperatorInfo(userPrincipals.getUsername(),updateTourOperatorDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping()
    public ResponseEntity<UpdateTourOperatorDTO> getTourOperatorInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(tourOperatorService.find(user.getId()));
    }


}
