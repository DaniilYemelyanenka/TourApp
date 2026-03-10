package by.sysoev.tourApp.controller;


import by.sysoev.tourApp.DTO.LastBookingUsersDTO;
import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.DTO.UpdateClientInfoDTO;
import by.sysoev.tourApp.entity.UserPrincipals;
import by.sysoev.tourApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/payments/stats")
    public ResponseEntity<List<PaymentsStatsDTO>> getClientsPaymentsStats(){
        log.info("Request for pyments stats");
        List<PaymentsStatsDTO> stats = clientService.getClientsPaymentsStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lastBookings")
    public ResponseEntity<List<LastBookingUsersDTO>> getLastBookingsUsers(){
        log.info("Request for for clients last bookings");
        List<LastBookingUsersDTO> list = clientService.getLastBookingUsers();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping()
    public ResponseEntity<String> updateClientInfo(@AuthenticationPrincipal UserPrincipals userPrincipals,
                                                   @RequestBody UpdateClientInfoDTO dto){
        clientService.updateClientInfo(userPrincipals.getUsername(),dto);
        return ResponseEntity.ok("success");
    }
}
