package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.MostPopularCityDTO;

import java.util.List;

public interface CityRepository {
    List<MostPopularCityDTO> getMostPopularCities();
}
