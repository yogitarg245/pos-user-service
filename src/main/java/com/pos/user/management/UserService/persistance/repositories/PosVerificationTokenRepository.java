package com.pos.user.management.UserService.persistance.repositories;

import com.pos.user.management.UserService.persistance.entities.PosVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosVerificationTokenRepository extends JpaRepository<PosVerificationToken,Long>,RevisionRepository<PosVerificationToken,Long,Long> {
}
