package org.example.havayolu.repository;

import org.example.havayolu.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByTc(String tc);

}
