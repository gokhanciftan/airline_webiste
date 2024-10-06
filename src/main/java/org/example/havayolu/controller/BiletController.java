package org.example.havayolu.controller;

import org.example.havayolu.dto.BiletDto;
import org.example.havayolu.dto.BiletResponseDto;
import org.example.havayolu.dto.CreateBiletDto;
import org.example.havayolu.dto.EmptySeatsRequestDto;
import org.example.havayolu.dto.ReservedBiletDto;
import org.example.havayolu.entity.Bilet;
import org.example.havayolu.service.BiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/bilet")
public class BiletController {

    private final BiletService biletService;

    @Autowired
    public BiletController(BiletService biletService) {
        this.biletService = biletService;
    }

    @PutMapping("/buy/{biletId}")
    public ResponseEntity<Void> buyTicket(@PathVariable("biletId") int biletId) {
        boolean success = biletService.buyTicket(biletId);
        if (success) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BiletDto>> listUserTickets(@PathVariable("userId") int userId) {
    	System.out.println(userId);
        List<BiletDto> userTickets = biletService.listUserTickets(userId);
        return ResponseEntity.ok(userTickets);
    }

    @DeleteMapping("/cancel/{biletId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("biletId") int biletId) {
        biletService.cancelReservation(biletId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/reserve")
    public ResponseEntity<ReservedBiletDto> reserveTicket(@RequestBody CreateBiletDto biletDto) {
        ReservedBiletDto reservedTicket = biletService.reservation(biletDto);
        return ResponseEntity.ok(reservedTicket);
    }

    @DeleteMapping("/refund/{biletId}")
    public ResponseEntity<Void> refundTicket(@PathVariable("biletId") int biletId) {
        biletService.refundTicket(biletId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/occupied-seats")
    public ResponseEntity<List<Integer>> findOccupiedSeats(@RequestBody EmptySeatsRequestDto request) {
        int ucusId = request.getUcusId();
        Date date = request.getDate();

        List<Integer> occupiedSeats = biletService.findOccupiedSeats(ucusId, date);
        return ResponseEntity.ok(occupiedSeats);
    }
}
