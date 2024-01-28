package com.scaler.ECommUserService.repository;

import com.scaler.ECommUserService.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenAndUserId(String token, Long id);
    Optional<Session> findById(Long id);
}
