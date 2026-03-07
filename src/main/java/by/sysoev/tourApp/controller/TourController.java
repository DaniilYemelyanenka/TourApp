package by.sysoev.tourApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tour")
public class TourController {

    @GetMapping("/")
    public ResponseEntity<String> getTour(){
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
