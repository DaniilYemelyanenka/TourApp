package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.DTO.LocationDTO;
import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.entity.Location;
import by.sysoev.tourApp.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("cities")
    public ResponseEntity<List<Location>> getCities(){
        List<Location> locations = cityService.getAll();
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLocation(@RequestBody LocationDTO location){
        cityService.add(location);
        return ResponseEntity.ok("success");
    }
}
