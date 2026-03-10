package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.entity.Transport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transport")
@RequiredArgsConstructor
public class TransportController {

    @GetMapping("")
    public ResponseEntity<List<Transport>> getAllTransport(){
        return ResponseEntity.ok(List.of());
    }
}
