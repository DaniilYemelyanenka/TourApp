package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.entity.Hotel;
import by.sysoev.tourApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> list = hotelService.getAll();
        return ResponseEntity.ok(list);
    }
}
