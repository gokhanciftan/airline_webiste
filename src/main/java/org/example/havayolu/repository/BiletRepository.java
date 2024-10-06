package org.example.havayolu.repository;

import java.util.List;

import org.example.havayolu.entity.Bilet;
import org.example.havayolu.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletRepository extends JpaRepository<Bilet,Integer> {

	List<Bilet> findByUserId(int userId);

	List<Bilet> findByUser(Users user);
	
	


}
