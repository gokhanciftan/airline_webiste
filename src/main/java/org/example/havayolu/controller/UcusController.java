package org.example.havayolu.controller;

import java.util.Date;
import java.util.List;

import org.example.havayolu.dto.CreateUcusDto;
import org.example.havayolu.dto.UcusDto;
import org.example.havayolu.entity.Ucus;
import org.example.havayolu.entity.enums.Sehir;
import org.example.havayolu.service.UcusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping
public class UcusController {
	private final UcusService ucusService;

    @Autowired
    public UcusController(UcusService ucusService) {
        this.ucusService = ucusService;
    }

    @GetMapping("/findFlights")
    public ResponseEntity<?> findSuitableFlights(
            @RequestParam("departureCity") Sehir departureCity,
            @RequestParam("arrivalCity") Sehir arrivalCity,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        
        List<UcusDto> suitableFlights = ucusService.findSuitableFlights(departureCity, arrivalCity, date);

        if (suitableFlights.isEmpty()) {
            return new ResponseEntity<>("No flights found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(suitableFlights, HttpStatus.OK);
    }

 
    @PostMapping
    public ResponseEntity<Ucus> createUcus(@RequestBody CreateUcusDto createUcus){
    	return new ResponseEntity<>(ucusService.create(createUcus),HttpStatus.CREATED);
    }
    
    
}
