package com.duvanlabrador.parking.Repository;

import com.duvanlabrador.parking.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

   @Query(value = "SELECT * FROM users u WHERE u.email=:email", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);
}
