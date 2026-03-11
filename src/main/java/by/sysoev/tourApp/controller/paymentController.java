package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.service.paymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class paymentController {

    private final paymentService service;

    @GetMapping("{id}/getTotalSumm")
    public ResponseEntity<Double> getTotalSumm(@PathVariable Long id){
        Double totalSumm = service.getBookingTotalSumm(id);
        return ResponseEntity.ok(Double.parseDouble("1234"));
    }

   @PostMapping("{bookingId}/make")
    public ResponseEntity<String> confirmPayment(@PathVariable Long bookingId){
        service.confirmPayment(bookingId);
        return ResponseEntity.ok("success");
   }
}
