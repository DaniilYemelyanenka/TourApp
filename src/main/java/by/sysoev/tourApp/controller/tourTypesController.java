package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.entity.TourType;
import by.sysoev.tourApp.service.TourtypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tour-types")
@RequiredArgsConstructor
public class tourTypesController {

    private final TourtypesService service;

    @GetMapping("")
    public ResponseEntity<List<TourType>> getTourTypes(){
        List<TourType> list  = service.getAll();
        return  ResponseEntity.ok(list);
    }
}
