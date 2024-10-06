package org.example.havayolu.service;

import org.example.havayolu.dto.BiletDto;
import org.example.havayolu.dto.CreateBiletDto;
import org.example.havayolu.dto.ReservedBiletDto;
import org.example.havayolu.entity.Bilet;
import org.example.havayolu.entity.Ucus;
import org.example.havayolu.entity.Users;
import org.example.havayolu.entity.enums.Roles;
import org.example.havayolu.repository.BiletRepository;
import org.example.havayolu.repository.UcusRepository;
import org.example.havayolu.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BiletService {

    private final BiletRepository biletRepository;
    private final UcusRepository ucusRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public BiletService(BiletRepository biletRepository, UcusRepository ucusRepository, UsersRepository usersRepository) {
        this.biletRepository = biletRepository;
        this.ucusRepository = ucusRepository;
        this.usersRepository = usersRepository;
    }

    // Rezervasyonu iptal eden metot
    public void cancelReservation(int biletId) {
        Optional<Bilet> biletOptional = biletRepository.findById(biletId);
        if (biletOptional.isPresent()) {
            Bilet bilet = biletOptional.get();
            if (bilet.getReserved() && bilet.getReservationTime() != null) {
                   biletRepository.delete(bilet); 
            }
        } else {
            throw new IllegalArgumentException("Ticket not found");
        }
    }
    


    public List<Integer> findOccupiedSeats(int ucusId, Date date) {
        // Tarihi gün, ay ve yıl olarak ayır
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH, Ocak için 0 döndürür, bu yüzden 1 ekliyoruz
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Belirli bir gün, ay ve yıl ile uçuşu al
        Ucus ucus = ucusRepository.findById(ucusId).orElseThrow(() -> new RuntimeException("Flight not found"));

        // Uçuşun biletlere bak
        List<Bilet> allTickets = ucus.getBilets();

        // Dolu koltuk numaralarını bul
        List<Integer> occupiedSeats = new ArrayList<>();
        for (Bilet bilet : allTickets) {
            // Biletin tarihi ile aranan tarih aynı mı kontrol et
            Calendar biletCal = Calendar.getInstance();
            biletCal.setTime(bilet.getUcus().getDate());
            int biletYear = biletCal.get(Calendar.YEAR);
            int biletMonth = biletCal.get(Calendar.MONTH) + 1;
            int biletDay = biletCal.get(Calendar.DAY_OF_MONTH);

            if (biletYear == year && biletMonth == month && biletDay == day) {
                occupiedSeats.add(bilet.getKoltukNo());
            }
        }

        // Dolu koltuk numaralarını içeren listeyi döndür
        return occupiedSeats;
    }



    // Bileti iade eden metot
    public void refundTicket(int biletId) {
        Optional<Bilet> biletOptional = biletRepository.findById(biletId);
        if (biletOptional.isPresent()) {
            Bilet bilet = biletOptional.get();
            if (!bilet.getReserved()) {
                // Geri ödeme mantığı burada işlenebilir (örneğin, kullanıcının bakiyesini güncelleme)

                biletRepository.delete(bilet);
            } else {
                throw new IllegalArgumentException("Reserved tickets cannot be refunded");
            }
        } else {
            throw new IllegalArgumentException("Ticket not found");
        }
    }

    // Bilet DTO'ya dönüştürme metodu
    private BiletDto convertToDto(Bilet bilet) {
        return new BiletDto(bilet.getId(), bilet.getUser().getId(), bilet.getKoltukNo(), bilet.getUcus().getId(),bilet.getReserved(),bilet.getReservationTime(),bilet.getCalcprice());
    }

    public boolean buyTicket(int biletId) {
        Optional<Bilet> biletOptional = biletRepository.findById(biletId);

        if (biletOptional.isPresent()) {
            Bilet bilet = biletOptional.get();
            bilet.setReserved(false);
            biletRepository.save(bilet);
            return true;
        } else {
            return false;
        }
    }
    public ReservedBiletDto reservation(CreateBiletDto biletDto) {
        Users user = usersRepository.findById(biletDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Ucus ucus = ucusRepository.findById(biletDto.getUcusId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        double calcprice = calculateTicketPrice(user, ucus.getPrice());
        if(biletDto.isGidisdonus()) {
        	calcprice=(calcprice*98)/100;
        }
        // Ensure that Bilet object is correctly created without setting the id
        Bilet bilet = new Bilet(user, biletDto.getKoltukNo(), ucus, calcprice,biletDto.isGidisdonus());
        bilet=biletRepository.save(bilet);

        // Return the BiletDto object with the generated id
        return new ReservedBiletDto(bilet.getId(),user.getId(), bilet.getKoltukNo(), ucus.getId(), calcprice,ucus.getPrice());
    }

    
 // Bilet fiyatını hesaplayan ve üyelere indirim uygulayan metot
    public double calculateTicketPrice(Users user, double basePrice) {
        double finalPrice = basePrice;
        Roles role = user.getRoles();

        switch (role) {
            case VIP:
                finalPrice *= 0.75; // %25 indirim
                break;
            case DAIMI:
                int numberOfBilets = user.getBilets().size();
                int discountSteps = numberOfBilets / 5;
                double discount = 0.1 * discountSteps; // Her 5 bilet için %10 indirim
                finalPrice *= (1 - Math.min(discount, 0.5)); // Maksimum %50 indirim uygulanır
                break;
            case NORMAL:
                // İndirim yok
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }

        return finalPrice;
    }

    public List<BiletDto> listUserTickets(int userId) {
    	System.out.println("1");
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    	System.out.println("2"+ user.getLastName());

        List<Bilet> bilets = biletRepository.findByUser(user);
        return bilets.stream()
                .map(bilet -> new BiletDto(bilet.getId(), bilet.getUser().getId(), bilet.getKoltukNo(), bilet.getUcus().getId(),bilet.getReserved(),bilet.getReservationTime(),bilet.getCalcprice()))
                .collect(Collectors.toList());
    }
    

    // Diğer metodlar (reserveTicket, cancelReservation, refundTicket, getTicketDetails) burada tanımlanabilir
}