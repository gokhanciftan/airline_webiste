package org.example.havayolu.repository;

import java.util.Date;
import java.util.List;

import org.example.havayolu.entity.Ucus;
import org.example.havayolu.entity.enums.Sehir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UcusRepository extends JpaRepository<Ucus,Integer> {
	
	List<Ucus> findByKalkısAndInıs(Sehir kalkıs, Sehir inıs);
	
    List<Ucus> findByKalkısAndInısAndDate(Sehir kalkıs, Sehir inıs, Date date);
    
}
