package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.LocationDTO;
import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.entity.Location;
import by.sysoev.tourApp.repository.impl.CityRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepositoryImpl cityRepository;

    public List<MostPopularCityDTO> getMostPopularCities(){
        return cityRepository.getMostPopularCities();
    }

    public List<Location> getAll(){
        return cityRepository.getAll();
    };

    public void add(LocationDTO location){
        cityRepository.add(location);
    }
}
