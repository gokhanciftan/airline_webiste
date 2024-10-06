package org.example.havayolu.service;

import org.example.havayolu.dto.BiletResponseDto;
import org.example.havayolu.dto.CreateUcusDto;
import org.example.havayolu.dto.UcusDetailResponseDto;
import org.example.havayolu.dto.UcusDto;

import org.example.havayolu.entity.enums.Sehir;
import org.example.havayolu.entity.Ucus;
import org.example.havayolu.entity.Users;
import org.example.havayolu.repository.UcusRepository;
import org.example.havayolu.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UcusService {
	
	@Autowired
    private UcusRepository ucusRepository;
	@Autowired
    private UsersRepository usersRepository;
	@Autowired
    private BiletService biletService;

   

    public List<UcusDto> listAvailableFlights(Sehir departureCity, Sehir arrivalCity, String departureDate) {
        List<Ucus> ucusbos = new ArrayList<Ucus>();
        List<Ucus> ucus = ucusRepository.findAll();
        Date mydate = convertToDate(departureDate);
        
        for(Ucus ucus2:ucus) {
        	if(ucus2.getDate().getDay() == mydate.getDay()
        			&& ucus2.getDate().getMonth() == mydate.getMonth() 
        			&& ucus2.getDate().getYear() == mydate.getYear()) {
        		ucusbos.add(ucus2);
        	}
        }
        
        return convertToDtoList(ucusbos);
    }
    
    public Date convertToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = null;
        try {
            dates = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            // Hata durumunda uygun bir işlem yapabilirsiniz.
        }
        return dates;
    }

    // Verilen kalkış, varış ve tarih bilgilerine göre uygun uçuşları bulur
    public List<UcusDto> findSuitableFlights(Sehir departureCity, Sehir arrivalCity, Date date) {
        List<Ucus> suitableFlights = ucusRepository.findByKalkısAndInıs(departureCity, arrivalCity);
        List<UcusDto> resultFlights = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH, Ocak için 0 döndürür, bu yüzden 1 ekliyoruz
        int day = cal.get(Calendar.DAY_OF_MONTH);

        for (Ucus flight : suitableFlights) {
            Date flightDate = flight.getDate();

            cal.setTime(flightDate);
            int flightYear = cal.get(Calendar.YEAR);
            int flightMonth = cal.get(Calendar.MONTH) + 1;
            int flightDay = cal.get(Calendar.DAY_OF_MONTH);

            if (flightYear == year && flightMonth == month && flightDay == day) {
                UcusDto flightDTO = new UcusDto();
                flightDTO.setId(flight.getId());
                flightDTO.setDate(flight.getDate());
                flightDTO.setKalkis(flight.getKalkıs());
                flightDTO.setInis(flight.getInıs());
                flightDTO.setPrice(flight.getPrice());
                resultFlights.add(flightDTO);
            }
        }

        if (resultFlights.isEmpty()) {
            return null;
        }

        return resultFlights;
    }




    // Kullanıcıya uygun uçuşları DTO listesine dönüştürür
    private List<UcusDto> convertToDtoList(List<Ucus> flights) {
        return flights.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Uçuşu DTO'ya dönüştürür
    private UcusDto convertToDto(Ucus flight) {
        return new UcusDto(flight.getId(), flight.getDate(), flight.getKalkıs(), flight.getInıs(), flight.getPrice());
    }
    
    public Ucus create(CreateUcusDto createUcusDto) {
    	Ucus ucus = new Ucus();
    	ucus.setInıs(createUcusDto.getInis());
    	ucus.setKalkıs(createUcusDto.getKalkis());
    	ucus.setPrice(createUcusDto.getPrice());
    	ucus.setDate(createUcusDto.getDate());
    	ucus.setGun(getDayName(createUcusDto.getDate()));
    	return ucusRepository.save(ucus);
    }
    
    public  String getDayName(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        return dayFormat.format(date);
    }
    
}