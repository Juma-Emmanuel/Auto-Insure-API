package io.api.AutoInsure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.api.AutoInsure.entity.User;




@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
    Optional <User> findByEmail(String username);

   
}
