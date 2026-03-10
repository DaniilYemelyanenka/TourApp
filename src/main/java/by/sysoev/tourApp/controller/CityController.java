package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.event.WindowFocusListener;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("mostPopular")
    public ResponseEntity<List<MostPopularCityDTO>> getMostPopularCities(){
        log.info("Request for most popular cities");
        List<MostPopularCityDTO> cityList = cityService.getMostPopularCities();
        return ResponseEntity.status(HttpStatus.OK).body(cityList);
    }
}
