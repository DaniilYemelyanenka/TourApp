package by.sysoev.tourApp.service;

import by.sysoev.tourApp.entity.Hotel;
import by.sysoev.tourApp.repository.impl.HotelRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepositoryImpl hotelRepository;

    public List<Hotel> getAll(){return hotelRepository.getAll();}
}
