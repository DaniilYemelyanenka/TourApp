package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("mostPopular")
    public ResponseEntity<List<MostPopularCityDTO>> getMostPopularCities(){
        List<MostPopularCityDTO> cityList = cityService.getMostPopularCities();
        return ResponseEntity.status(HttpStatus.OK).body(cityList);
    }
}
