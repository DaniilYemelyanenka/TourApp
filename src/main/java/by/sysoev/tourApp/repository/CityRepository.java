package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.LocationDTO;
import by.sysoev.tourApp.DTO.MostPopularCityDTO;
import by.sysoev.tourApp.entity.Location;

import java.util.List;

public interface CityRepository {
    List<MostPopularCityDTO> getMostPopularCities();

    List<Location> getAll();

    void add(LocationDTO dto);
}
