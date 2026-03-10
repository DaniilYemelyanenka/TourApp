package by.sysoev.tourApp.controller;

import by.sysoev.tourApp.service.PDFService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final PDFService pdfService;

    @GetMapping("")
    public ResponseEntity<byte[]> getTicket() {
        try {
            byte[] pdf = pdfService.generateTickets();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition", "attachment; filename=ticket.pdf")
                    .body(pdf);
        } catch (IOException ex) {
            log.error("Error creating pdf: {}",ex.getMessage());
        }
        return null;
    }
}
