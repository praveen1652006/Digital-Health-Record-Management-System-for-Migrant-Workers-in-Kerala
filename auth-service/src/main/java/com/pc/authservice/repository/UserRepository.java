package com.pc.authservice.repository;

import com.pc.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public  interface UserRepository extends JpaRepository<User, UUID> {

<<<<<<< Updated upstream
    @Query("SELECT d FROM User d WHERE  d.identifier = :identifier OR d.mobileNumber = :identifier")
=======
    @Query("SELECT d FROM User d WHERE  d.identifier = :identifier OR d.mobileNumber = :identfier")
>>>>>>> Stashed changes
    Optional<User> findByIdentifier(String identifier);
}
