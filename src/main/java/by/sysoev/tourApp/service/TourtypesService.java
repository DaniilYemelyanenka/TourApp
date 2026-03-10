package by.sysoev.tourApp.service;

import by.sysoev.tourApp.entity.TourType;
import by.sysoev.tourApp.repository.impl.TourTypesRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourtypesService {
    private final TourTypesRepositoryImpl repository;

    public List<TourType> getAll(){return repository.getAll();}
}
